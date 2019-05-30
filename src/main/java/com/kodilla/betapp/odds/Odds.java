package com.kodilla.betapp.odds;

import com.kodilla.betapp.match.Match;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Odds {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESULT")
    @NotNull
    private Result result;

    @Column(name = "MATCH_ODDS")
    private BigDecimal matchOdds;
    private Match match;
}