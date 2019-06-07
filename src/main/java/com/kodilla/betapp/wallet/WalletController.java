package com.kodilla.betapp.wallet;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {
    private WalletMapper walletMapper;
    private WalletService walletService;

    @PostMapping
    long initWallet(@RequestBody WalletDto walletDto) {
        log.info("Init wallet called. WalletDto [{}]", walletDto);

        Wallet wallet = walletService.initWallet(walletMapper.mapToWallet(walletDto));

        return wallet.getId();
    }

    @PatchMapping("/add/{funds}/{id}")
    WalletDto addFunds(@PathVariable BigDecimal funds, @PathVariable long id) {
        log.info("Add funds to wallet with id [{}]", id);

        return walletMapper.mapToWalletDto(walletService.addFunds(id, funds));
    }

    @PatchMapping("/sub/{deduct}/{id}")
    WalletDto deductFunds(@PathVariable BigDecimal deduct, @PathVariable long id) {
        log.info("Cash out funds from wallet with id [{}]", id);

        return walletMapper.mapToWalletDto(walletService.deductFunds(id, deduct));
    }

    @PatchMapping("/{currency}/{id}")
    WalletDto changeCurrency(@PathVariable Currency currency, @PathVariable long id) {
        log.info("Change currency to [{}] in wallet with id [{}]", currency, id);

        return walletMapper.mapToWalletDto(walletService.changeCurrency(id, currency));
    }

}
