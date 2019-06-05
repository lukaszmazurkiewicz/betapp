package com.kodilla.betapp.event;

public interface EventServiceInterface {
    Event addEvent(Event event);
    Event updateEvent(Long id);
    void deleteEvent(Long id);
}
