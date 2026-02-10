package com.adacapstone.seattleinyourlens.controller;

import com.adacapstone.seattleinyourlens.entity.Event;
import com.adacapstone.seattleinyourlens.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@CrossOrigin("*")

// getting the data from frontend

public class EventController {

    private final EventService eventService;

    // Create a new event
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.postEvent(event);
    }

    @GetMapping
    // retrieves the "Creator" and "Comments" at once, more efficient
    // not making many small, slow calls
    public List<Event> getAllEvents() {
        return eventService.findAllWithDetails();
    }


    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
    @PatchMapping("/{id}/like")
    public Event likeEvent(@PathVariable Long id) {
        return eventService.likeEvent(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    // Inside your EventController.java

    @GetMapping("/search")
    public List<Event> searchEvents(@RequestParam String query) {
        // This receives the 'query' from the URL: /api/events/search?query=seattle
        return eventService.searchEvents(query);
    }
}
