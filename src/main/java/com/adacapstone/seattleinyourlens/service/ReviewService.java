package com.adacapstone.seattleinyourlens.service;

import com.adacapstone.seattleinyourlens.entity.Event;
import com.adacapstone.seattleinyourlens.entity.Review;
import com.adacapstone.seattleinyourlens.repository.EventRepository;
import com.adacapstone.seattleinyourlens.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final EventRepository eventRepository;

    public List<Review> getReviewsByEvent(Long eventId) {
        return reviewRepository.findByEvent_Event_id(eventId);
    }

    public Review addReview(Long eventId, Review review) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        review.setEvent(event);
        return reviewRepository.save(review);
    }
}
