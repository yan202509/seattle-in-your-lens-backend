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

public class EventController {

    private final EventService eventService;


    // Create a new event
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.postEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
