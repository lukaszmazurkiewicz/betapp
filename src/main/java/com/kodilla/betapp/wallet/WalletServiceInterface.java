package com.kodilla.betapp.wallet;

import java.math.BigDecimal;

public interface WalletServiceInterface {
    void initWallet();
    BigDecimal payment();
    BigDecimal withdrawal();
    void changeCurrency();
}
