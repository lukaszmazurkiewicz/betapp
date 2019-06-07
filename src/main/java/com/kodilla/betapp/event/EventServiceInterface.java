package com.kodilla.betapp.event;

import java.util.List;

public interface EventServiceInterface {
    Event getEventById(long id);
    Event addEvent(Event event);
    Event updateEvent(long id);
    void deleteEvent(long id);
    List<Event> getAllEvents();
}
