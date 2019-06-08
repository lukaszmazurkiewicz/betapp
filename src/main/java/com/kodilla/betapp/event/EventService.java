package com.kodilla.betapp.event;

import com.kodilla.betapp.match.Match;
import com.kodilla.betapp.match.MatchRepository;
import com.kodilla.betapp.odds.Odds;
import com.kodilla.betapp.odds.OddsRepository;
import com.kodilla.betapp.odds.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService implements EventServiceInterface {
    private final EventRepository eventRepository;
    private final MatchRepository matchRepository;
    private final OddsRepository oddsRepository;

    @Override
    public Event getEventById(long id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event with given id " + id + " not found."));
    }

    @Override
    public Event addEvent(Event event) {
        BigDecimal odds = oddsRepository.findByResultAndMatchId(event.getBet(), event.getMatch().getId()).getMatchOdds();
        event.setBetOdds(odds);
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(long id) {
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
    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


}
