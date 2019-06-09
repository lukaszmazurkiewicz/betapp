package com.kodilla.betapp.odds;

import com.kodilla.betapp.match.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ODDS")
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

    @ManyToOne
    @JoinColumn(name = "MATCH_ID")
    private Match match;

    public Odds(Result result, BigDecimal matchOdds) {
        this.result = result;
        this.matchOdds = matchOdds;
    }
}
