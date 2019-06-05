package com.kodilla.betapp.event;

import com.kodilla.betapp.coupon.Coupon;
import com.kodilla.betapp.match.Match;
import com.kodilla.betapp.match.MatchService;
import com.kodilla.betapp.user.User;
import com.kodilla.betapp.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EventMapper {
    private final UserService userService;
    private final MatchService matchService;

    Event mapToEvent(final EventDto eventDto) {
        User user = userService.getUserById(eventDto.getUserId());
        Match match = matchService.getMatchById(eventDto.getMatchId());

        return new Event(
                eventDto.getId(),
                eventDto.getBet(),
                eventDto.isWin(),
                user,
                match
                //new Coupon() //TODO fixed when coupon implemented
        );
    }

    EventDto mapToEventDto(final Event event) {
        return new EventDto(
                event.getId(),
                event.getBet(),
                event.isWin(),
                event.getUser().getId(),
                event.getMatch().getId()
                //22L  //TODO fixed when coupon implemented
        );
    }
}