package com.kodilla.betapp.wallet;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class WalletService implements WalletServiceInterface {
    private final WalletRepository walletRepository;

    public Wallet getWalletById(long id) {
        return walletRepository.findById(id).orElseThrow(() -> new WalletNotFoundException("Wallet with id " + id + "not found." ));
    }

    @Override
    public Wallet initWallet(Wallet wallet) {
        return walletRepository.save(wallet);

    }

    @Override
    public Wallet addFunds(Long id, BigDecimal payment) {
        Wallet wallet = getWalletById(id);
        log.info(" " + wallet.getAccountBalance() + "  " + payment);
        wallet.getAccountBalance().add(payment);

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet deductFunds(Long id, BigDecimal withdrawal) {
        Wallet wallet = getWalletById(id);
        wallet.getAccountBalance().subtract(withdrawal);

        return  walletRepository.save(wallet);
    }

    @Override
    public Wallet changeCurrency(Long id, Currency currency) {
        Wallet wallet = getWalletById(id);
        wallet.setCurrency(currency);

        return walletRepository.save(wallet);

    }
}
