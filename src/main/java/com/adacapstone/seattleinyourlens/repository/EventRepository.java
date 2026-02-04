package com.adacapstone.seattleinyourlens.repository;


import com.adacapstone.seattleinyourlens.entity.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// what kind of data we are looking for from database

//@Repository
//public interface EventRepository extends JpaRepository<Event, Long> {
//    @EntityGraph(attributePaths = {"creator", "reviews"})
//    List<Event> findByEventTitleContainingIgnoreCase(String title);
//}

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // This tells Hibernate: "Be EAGER just for the homepage"
    @EntityGraph(attributePaths = {"creator", "reviews"})
    @Query("SELECT e FROM Event e")
    List<Event> findAllWithDetails();

    // a single event
    @EntityGraph(attributePaths = {"creator", "reviews"})
    @Query("SELECT e FROM Event e WHERE e.event_id = :id")
    Optional<Event> findByIdWithDetails(@Param("id") Long id);

    // This tells Hibernate: "Be EAGER just for this one search"
    @EntityGraph(attributePaths = {"creator", "reviews"})
    List<Event> findByEventTitleContainingIgnoreCase(String title);


}