package com.adacapstone.seattleinyourlens.service;

import com.adacapstone.seattleinyourlens.entity.Event;
import com.adacapstone.seattleinyourlens.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


// use Mockito test (unit test) is faster than springboot test,
// without accessing the actual database

@ExtendWith(MockitoExtension.class) // This tells JUnit we are using Mocks, not a real DB
class EventServiceTest {

    @Mock
    private EventRepository eventRepository; // The "Fake" Repository

    @InjectMocks
    private EventService eventService; // The Service we are testing

    @Test
    void testLikeEventIncrementsLikes() {
        // 1. Arrange: Create a fake event with 0 likes
        // only create 1 Long, so test is for 1 Long
        Event fakeEvent = new Event();
        fakeEvent.setEvent_id(1L);
        fakeEvent.setLikes(0);

        // Tell the mock: when service calls "findByIdWithDetails", give it our fake event
        when(eventRepository.findByIdWithDetails(1L)).thenReturn(Optional.of(fakeEvent));
        // Tell the mock: when it saves, just return the event back
        when(eventRepository.save(any(Event.class))).thenReturn(fakeEvent);

        // 2. Act: Call the service method
        Event updatedEvent = eventService.likeEvent(1L);

        // 3. Assert: Did the likes go up?
        assertEquals(1, updatedEvent.getLikes());

        // 4. Verify: Did the service actually tell the database to save the change?
        verify(eventRepository).save(fakeEvent);
    }

    @Test
    void testGetEventByIdThrowsExceptionWhenNotFound() {
        // Arrange: Make the repository return "Empty"
        when(eventRepository.findByIdWithDetails(2L)).thenReturn(Optional.empty());

        // Act & Assert: Check if it throws the RuntimeException you wrote in your Service
        Exception exception = assertThrows(RuntimeException.class, () -> {
            eventService.getEventById(2L);
        });

        // because we only have 1L, 2L will never exist here
        assertEquals("Event not found with id 2", exception.getMessage());
    }

    @Test
    void testDeleteEvent() {
        // Act
        eventService.deleteEvent(1L);

        // Assert: In delete, we just verify the repository's delete method was called
        verify(eventRepository, times(1)).deleteById(1L);
    }
}