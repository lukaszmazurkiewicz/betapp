package com.kodilla.betapp.event;

import com.kodilla.betapp.coupon.Coupon;
import com.kodilla.betapp.match.Match;
import com.kodilla.betapp.odds.Result;
import com.kodilla.betapp.user.User;
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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "EVENTS")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "BET")
    @NotNull
    private Result bet;

    @Setter
    @Column(name = "BET_ODDS")
    private BigDecimal betOdds;

    @Setter
    @Column(name = "WIN")
    private boolean win;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "MATCH_ID")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "COUPON_ID")
    private Coupon coupon;

    public Event(Result bet, BigDecimal betOdds, boolean win) {
        this.bet = bet;
        this.betOdds = betOdds;
        this.win = win;
    }
}
