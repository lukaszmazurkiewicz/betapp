package com.kodilla.betapp.event;

import com.kodilla.betapp.odds.Result;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class EventDto {
    private long id;
    private Result bet;
    private boolean win;
    private long userId;
    private long matchId;
    private long couponId;

    public EventDto(long id, Result bet, boolean win, long userId, long matchId) {
        this.id = id;
        this.bet = bet;
        this.win = win;
        this.userId = userId;
        this.matchId = matchId;
    }
}
