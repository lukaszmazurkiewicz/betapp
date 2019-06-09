package com.kodilla.betapp.wallet;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WalletService implements WalletServiceInterface {
    private final WalletRepository walletRepository;

    @Override
    public Wallet getWalletById(long id) {
        return walletRepository.findById(id).orElseThrow(() -> new WalletNotFoundException("Wallet with id " + id + " not found." ));
    }

    @Override
    public Wallet initWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet addFunds(long id, BigDecimal payment) {
        Wallet wallet = getWalletById(id);
        wallet.setAccountBalance(wallet.getAccountBalance().add(payment));

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet deductFunds(long id, BigDecimal withdrawal) {
        Wallet wallet = getWalletById(id);
        wallet.setAccountBalance(wallet.getAccountBalance().subtract(withdrawal));

        return  walletRepository.save(wallet);
    }

    @Override
    public Wallet changeCurrency(long id, Currency currency) {
        Wallet wallet = getWalletById(id);
        wallet.setCurrency(currency);

        return walletRepository.save(wallet);

    }
}
