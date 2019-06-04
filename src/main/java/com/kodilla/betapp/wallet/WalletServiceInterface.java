package com.kodilla.betapp.wallet;

import java.math.BigDecimal;

public interface WalletServiceInterface {
    Wallet initWallet(Wallet wallet);
    Wallet addFunds(Long id, BigDecimal payment);
    Wallet deductFunds(Long id, BigDecimal withdrawal);
    Wallet changeCurrency(Long id, Currency currency);
}
