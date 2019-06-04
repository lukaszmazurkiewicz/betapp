package com.kodilla.betapp.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
    private long id;
    private BigDecimal accountBallance;
    private Currency currency;
}
