package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Odds;
import com.kodilla.betapp.odds.Result;
import com.kodilla.betapp.team.Team;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "MATCHES")
public class Match {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "MATCH_DATE")
    @NotNull
    private LocalDate matchDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "END_RESULT")
    private Result endResult;

    @OneToMany (
            targetEntity = Team.class,
            mappedBy = "match",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Team> hostTeams;

    @OneToMany (
            targetEntity = Team.class,
            mappedBy = "match",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Team> guestTeams;

    @OneToMany (
            targetEntity = Odds.class,
            mappedBy = "match",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Odds> oddses;
}
