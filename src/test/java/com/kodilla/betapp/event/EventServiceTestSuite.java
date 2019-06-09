package com.kodilla.betapp.event;

import com.kodilla.betapp.odds.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTestSuite {
    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Test
    public void testGetEventById() {
        //Given
        Event event = new Event(Result.DRAW, BigDecimal.ONE, true);

        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));

        //When
        Event testEvent = eventService.getEventById(1L);

        //Then
        assertEquals(event.getId(), testEvent.getId());
        assertEquals(event.getBet(), testEvent.getBet());
        assertEquals(event.getBetOdds(), testEvent.getBetOdds());
        assertEquals(event.isWin(), testEvent.isWin());
    }

    @Test(expected = EventNotFoundException.class)
    public void testGetEventByIdThrewException() {
        //Given
        Event event = new Event(Result.DRAW, BigDecimal.ONE, true);

        //When
        Event testEvent = eventService.getEventById(1L);

        //Then
    }

    @Test
    public void testDeleteEvent() {
        //Given
        long sizeOfDatabaseBeforeTest = eventRepository.count();

        Event event = new Event(Result.DRAW, BigDecimal.ONE, true);

        //When
        eventService.deleteEvent(event.getId());

        //Then
        assertEquals(0, eventRepository.count() - sizeOfDatabaseBeforeTest);
    }

    @Test
    public void testGetAllEvents() {
        //Given
        Event event = new Event(Result.DRAW, BigDecimal.ONE, true);
        Event event2 = new Event(Result.DRAW, BigDecimal.ONE, false);

        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event2);

        when(eventRepository.findAll()).thenReturn(events);

        //When
        List<Event> testEventsList = eventService.getAllEvents();

        //Then
        assertEquals(2, testEventsList.size());
        assertTrue(testEventsList.contains(event));
        assertTrue(testEventsList.contains(event2));
    }
}