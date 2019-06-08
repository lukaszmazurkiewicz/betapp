package com.kodilla.betapp.event;

import com.kodilla.betapp.odds.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDto {
    private long id;
    private Result bet;
    private BigDecimal betOdds;
    private boolean win;
    private long userId;
    private long matchId;
    private long couponId;
}
