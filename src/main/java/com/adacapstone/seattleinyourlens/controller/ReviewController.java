package com.adacapstone.seattleinyourlens.controller;

import com.adacapstone.seattleinyourlens.entity.Review;
import com.adacapstone.seattleinyourlens.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events/{eventId}/reviews")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<Review> getReviews(@PathVariable Long eventId) {
        return reviewService.getReviewsByEvent(eventId);
    }

    @PostMapping
    public Review addReview(
            @PathVariable Long eventId,
            @RequestBody Review review
    ) {
        return reviewService.addReview(eventId, review);
    }
}
