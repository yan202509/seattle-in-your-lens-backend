package com.adacapstone.seattleinyourlens.repository;

import jakarta.transaction.Transactional; // Use Jakarta for modern Spring Boot
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
@Transactional // rolled back after the test finishes
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void testQueryRuns() {
        // Even if you saved a new Review here, it would disappear
        // from your database the moment this method finished!
        var results = reviewRepository.findByEvent_Event_id(999L);
        assertNotNull(results, "The results list should not be null");
    }
}