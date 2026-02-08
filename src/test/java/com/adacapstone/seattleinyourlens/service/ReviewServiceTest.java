package com.adacapstone.seattleinyourlens.service;

import com.adacapstone.seattleinyourlens.entity.Event;
import com.adacapstone.seattleinyourlens.entity.Review;
import com.adacapstone.seattleinyourlens.repository.EventRepository;
import com.adacapstone.seattleinyourlens.repository.ReviewRepository;
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
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void testGetReviewsByEventReturnsSavedReview() {
        // Setup an Event
        Event event = new Event();
        event.setEventTitle("Space Needle");
        event = eventRepository.save(event);

        // Setup a Review for that Event
        Review review = new Review();
        review.setEvent(event);
        review.setComment("Great view!");
        review.setRating(5);
        reviewRepository.save(review);

        // Call the SERVICE method (this is what we are testing)
        List<Review> results = reviewService.getReviewsByEvent(event.getEvent_id());

        // Assertions
        assertFalse(results.isEmpty(), "Should return at least one review");
        assertEquals(1, results.size());
        assertEquals("Great view!", results.get(0).getComment());
        assertEquals(event.getEvent_id(), results.get(0).getEvent().getEvent_id());
    }

    @Test
    void testAddReviewAttachesEventAndSaves() {
        // Setup Event
        Event event = new Event();
        event.setEventTitle("Space Needle");
        event = eventRepository.save(event);

        // Create Review WITHOUT event
        Review review = new Review();
        review.setComment("Great view!");
        review.setRating(5);

        // Call service method
        Review savedReview = reviewService.addReview(event.getEvent_id(), review);

        // Assertions
        assertNotNull(savedReview.getId(), "Saved review should have an ID");
        assertEquals("Great view!", savedReview.getComment());
        assertEquals(event.getEvent_id(), savedReview.getEvent().getEvent_id());
    }
}
