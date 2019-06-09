package com.kodilla.betapp.event;

import com.kodilla.betapp.odds.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTestSuite {
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    @Test
    public void testFindAll() {
        //Given
        long sizeOfDatabaseBeforeTest = eventRepository.count();

        Event event = new Event(Result.DRAW, BigDecimal.ONE, true);
        Event event2 = new Event(Result.DRAW, BigDecimal.ONE, false);

        eventRepository.save(event);
        eventRepository.save(event2);

        //When
        List<Event> events = eventRepository.findAll();

        //Then
        assertEquals(2, events.size() - sizeOfDatabaseBeforeTest);
        assertTrue(events.contains(event));
        assertTrue(events.contains(event2));
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Event event = new Event(Result.DRAW, BigDecimal.ONE, true);
        
        eventRepository.save(event);

        //When
        Optional<Event> testEvent = eventRepository.findById(event.getId());

        //Then
        assertTrue(testEvent.isPresent());
        assertEquals(Optional.of(event), testEvent);
        assertEquals(Result.DRAW, testEvent.get().getBet());
        assertEquals(BigDecimal.ONE, testEvent.get().getBetOdds());
        assertTrue(testEvent.get().isWin());
    }

    @Transactional
    @Test
    public void testSave() {
        //Given
        long sizeOfDatabaseBeforeTest = eventRepository.count();

        Event event = new Event(Result.DRAW, BigDecimal.ONE, true);
        Event event2 = new Event(Result.DRAW, BigDecimal.ONE, false);

        //When
        eventRepository.save(event);
        eventRepository.save(event2);

        //Then
        assertEquals(2L, eventRepository.count() - sizeOfDatabaseBeforeTest);
    }

}