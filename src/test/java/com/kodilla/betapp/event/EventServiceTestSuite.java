package com.kodilla.betapp.event;

import com.kodilla.betapp.odds.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
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
}