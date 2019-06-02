package com.kodilla.betapp.wallet;

import org.springframework.stereotype.Service;

@Service
public class WalletMapper {
    Wallet mapToWallet(final WalletDto walletDto) {
        return new Wallet(
                walletDto.getId(),
                walletDto.getAccountBallance(),
                walletDto.getCurrency()
        );
    }
}
