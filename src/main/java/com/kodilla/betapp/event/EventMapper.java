package com.kodilla.betapp.event;

import com.kodilla.betapp.coupon.Coupon;
import com.kodilla.betapp.coupon.CouponService;
import com.kodilla.betapp.match.Match;
import com.kodilla.betapp.match.MatchService;
import com.kodilla.betapp.user.User;
import com.kodilla.betapp.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EventMapper {
    private final UserService userService;
    private final MatchService matchService;
    private final CouponService couponService;

    Event mapToEvent(final EventDto eventDto) {
        User user = userService.getUserById(eventDto.getUserId());
        Match match = matchService.getMatchById(eventDto.getMatchId());
        Coupon coupon = couponService.getCouponById(eventDto.getCouponId());

        return new Event(
                eventDto.getId(),
                eventDto.getBet(),
                eventDto.isWin(),
                user,
                match,
                coupon
        );
    }

    EventDto mapToEventDto(final Event event) {
        return new EventDto(
                event.getId(),
                event.getBet(),
                event.isWin(),
                event.getUser().getId(),
                event.getMatch().getId(),
                event.getCoupon().getId()
        );
    }

    List<EventDto> mapToEventDtoList(final List<Event> events) {
        return events.stream()
                .map(e -> mapToEventDto(e))
                .collect(Collectors.toList());
    }
}
