package com.kodilla.betapp.coupon;

import com.kodilla.betapp.event.Event;
import com.kodilla.betapp.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "COUPONS")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "STAKE")
    @NotNull
    private BigDecimal stake;

    @Setter
    @Column(name = "WINNER")
    private boolean winner;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany (
            targetEntity = Event.class,
            mappedBy = "coupon",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Event> events;

    public Coupon(@NotNull BigDecimal stake, boolean winner) {
        this.stake = stake;
        this.winner = winner;
    }
}
