package com.kodilla.betapp.team;

import com.kodilla.betapp.match.Match;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "TEAMS")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "MATCH_ID")
    private Match match;

}
