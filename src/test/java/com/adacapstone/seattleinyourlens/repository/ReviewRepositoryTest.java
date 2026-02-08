package com.adacapstone.seattleinyourlens.repository;

import com.adacapstone.seattleinyourlens.entity.Event;
import com.adacapstone.seattleinyourlens.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import jakarta.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testFindByEventIdReturnsSavedReview() {
        // Setup an Event
        Event event = new Event();
        event.setEventTitle("Space Needle");
        event = eventRepository.save(event);

        // Setup a Review for that Event
        Review review = new Review();
        review.setEvent(event);
        review.setComment("Great view!");
        review.setRating(5);
        reviewRepository.save(review); // Remember to save!

        // Run the query you wrote in the Repository
        List<Review> results = reviewRepository.findByEvent_Event_id(event.getEvent_id());

        // Check the results
        assertFalse(results.isEmpty(), "The list should contain the review we just saved");
        assertEquals(1, results.size());
        assertEquals("Great view!", results.get(0).getComment());
        assertEquals(event.getEvent_id(), results.get(0).getEvent().getEvent_id());
    }

    @Test
    void testFindByEventIdReturnsEmptyForWrongId() {
        // Run query with an ID that doesn't exist (0)
        List<Review> results = reviewRepository.findByEvent_Event_id(0L);

        // Check that it's empty
        assertTrue(results.isEmpty(), "Should be empty because no reviews exist for ID 0");
    }
}