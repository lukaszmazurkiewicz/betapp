package com.kodilla.betapp.wallet;

import java.math.BigDecimal;

public interface WalletServiceInterface {
    Wallet getWalletById(long id);
    Wallet initWallet(Wallet wallet);
    Wallet addFunds(long id, BigDecimal payment);
    Wallet deductFunds(long id, BigDecimal withdrawal);
    Wallet changeCurrency(long id, Currency currency);
}
