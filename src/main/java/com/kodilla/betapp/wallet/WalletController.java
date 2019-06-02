package com.kodilla.betapp.wallet;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {
    private WalletMapper walletMapper;
    private WalletService walletService;

    @PostMapping
    Long initWallet(@RequestBody WalletDto walletDto) {
        log.info("Init wallet called. WalletDto [{}]", walletDto);

        Wallet wallet = walletService.initWallet(walletMapper.mapToWallet(walletDto));

        return wallet.getId();
    }
}
