package com.adacapstone.seattleinyourlens.service;

import com.adacapstone.seattleinyourlens.entity.Event;
import com.adacapstone.seattleinyourlens.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// what do we want to do with this data coming from frontend

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event postEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAllWithDetails() {
        // This calls the @EntityGraph method in your Repo
        // earlier it was only getAllEvents which cost
        return eventRepository.findAllWithDetails();
    }

    // unused because used findAllWithDetails() instead of getAllEvents
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id " + id));
    }

    public Event likeEvent(Long id) {
        Event event = getEventById(id);
        event.setLikes(event.getLikes() + 1);
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // for search bar; look in sql for the data
    public List<Event> searchEvents(String query) {
        return eventRepository.findByEventTitleContainingIgnoreCase(query);
    }


}
