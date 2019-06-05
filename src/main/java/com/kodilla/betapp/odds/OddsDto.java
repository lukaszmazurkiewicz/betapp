package com.kodilla.betapp.odds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OddsDto {
    private long id;
    private Result result;
    private BigDecimal matchOdds;
    private long matchId;
}
