package com.kodilla.betapp.wallet;

import java.math.BigDecimal;

public interface WalletServiceInterface {
    Wallet initWallet(Wallet wallet);
    BigDecimal payment();
    BigDecimal withdrawal();
    void changeCurrency();
}
