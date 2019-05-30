package com.kodilla.betapp.wallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "WALLET")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ACCOUNT_BALANCE")
    @NotNull
    private BigDecimal accountBalance;

    @Column(name = "CURRENCY")
    @NotNull
    private Currency currency;

}
