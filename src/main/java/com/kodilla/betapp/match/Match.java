package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Odds;
import com.kodilla.betapp.odds.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MATCHES")
public class Match {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "HOST_TEAM")
    @NotNull
    private String hostTeam;

    @Column(name = "GUEST_TEAM")
    @NotNull
    private String guestTeam;

    @Column(name = "MATCH_DATE")
    @NotNull
    private LocalDate matchDate;

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(name = "END_RESULT")
    private Result endResult;

    @OneToMany (
            targetEntity = Odds.class,
            mappedBy = "match",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Odds> odds;

    public Match(long id, String hostTeam, String guestTeam, LocalDate matchDate, Result endResult) {
        this.id = id;
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
        this.matchDate = matchDate;
        this.endResult = endResult;
    }
}
