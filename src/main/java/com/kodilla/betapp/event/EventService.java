package com.kodilla.betapp.event;

import com.kodilla.betapp.match.Match;
import com.kodilla.betapp.match.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService implements EventServiceInterface {
    private final EventRepository eventRepository;
    private final MatchRepository matchRepository;

    public Event getEventById(long id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event with given id " + id + " not found."));
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        Optional<Match> match = matchRepository.findById(event.get().getMatch().getId());
        if (event.get().getBet() == match.get().getEndResult()) {
            event.get().setWin(true);
        } else {
            event.get().setWin(false);
        }

        return eventRepository.save(event.get());
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }


}
