package com.kodilla.betapp.wallet;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WalletService implements WalletServiceInterface {
    private final WalletRepository walletRepository;
    @Override
    public Wallet initWallet(Wallet wallet) {
        return walletRepository.save(wallet);

    }

    @Override
    public BigDecimal payment() {
        return null;
    }

    @Override
    public BigDecimal withdrawal() {
        return null;
    }

    @Override
    public void changeCurrency() {

    }
}
