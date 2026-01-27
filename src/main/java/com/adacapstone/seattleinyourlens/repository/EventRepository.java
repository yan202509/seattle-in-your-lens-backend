package com.adacapstone.seattleinyourlens.repository;


import com.adacapstone.seattleinyourlens.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
