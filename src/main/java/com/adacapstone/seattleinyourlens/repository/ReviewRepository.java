package com.adacapstone.seattleinyourlens.repository;


import com.adacapstone.seattleinyourlens.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.event.event_id = :eventId")
    List<Review> findByEvent_Event_id(Long eventId);
}

