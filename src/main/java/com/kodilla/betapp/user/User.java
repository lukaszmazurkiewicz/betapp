package com.kodilla.betapp.user;

import com.kodilla.betapp.coupon.Coupon;
import com.kodilla.betapp.event.Event;
import com.kodilla.betapp.wallet.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "LOGIN")
    @NotNull
    private String login;

    @Setter
    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "WALLET_ID")
    private Wallet wallet;

    @OneToMany (
            targetEntity = Event.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Event> events;

    @OneToMany (
            targetEntity = Coupon.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Coupon> coupons;

    public User(long id, String login, String password, Wallet wallet) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.wallet = wallet;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(@NotNull String login, @NotNull String password, Wallet wallet) {
        this.login = login;
        this.password = password;
        this.wallet = wallet;
    }
}
