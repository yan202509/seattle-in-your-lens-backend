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
@ActiveProfiles("local")  // Uses local DB config
@Transactional             // Ensures rollback after each test
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testFindByEventIdReturnsSavedReview() { // we no longer need public before void
        // Create and save a new Event
        Event event = new Event();
        event.setEventTitle("Sample Event");
        event.setEvent_description("This is a sample event description");
        event = eventRepository.save(event);

        // Create and save a Review linked to that Event
        Review review = new Review();
        review.setEvent(event);
        review.setComment("Amazing experience!");
        review.setRating(2);
        reviewRepository.save(review);

        // Use your repository query
        List<Review> results = reviewRepository.findByEvent_Event_id(event.getEvent_id());

        // Assertions
        assertNotNull(results, "Results list should not be null");
        assertEquals(1, results.size(), "There should be exactly one review returned");
        assertEquals("Amazing experience!", results.get(0).getComment(), "Comment should match saved review");
        assertEquals(2, results.get(0).getRating(), "Rating should match saved review");
        assertEquals(event.getEvent_id(), results.get(0).getEvent().getEvent_id(), "Event ID should match");
    }

    @Test
    void testFindByEventIdReturnsEmptyForNonexistentEvent() {
        // Query a non-existent event ID but still return an empty string not just null
        List<Review> results = reviewRepository.findByEvent_Event_id(0L); // 0 usually not exist in database
        assertNotNull(results, "Results list should not be null");
        assertTrue(results.isEmpty(), "Results list should be empty for non-existent event");
    }
}
