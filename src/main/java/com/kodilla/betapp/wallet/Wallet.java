package com.kodilla.betapp.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "WALLET")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @Column(name = "ACCOUNT_BALANCE")
    @NotNull
    private BigDecimal accountBalance;

    @Setter
    @Column(name = "CURRENCY")
    @NotNull
    private Currency currency;

    public Wallet(BigDecimal accountBalance, Currency currency) {
        this.accountBalance = accountBalance;
        this.currency = currency;
    }
}
