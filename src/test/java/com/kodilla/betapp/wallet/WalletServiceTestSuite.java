package com.kodilla.betapp.wallet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WalletServiceTestSuite {
    @InjectMocks
    private WalletService walletService;

    @Mock
    private WalletRepository walletRepository;

    @Test
    public void testInitWallet() {
        //Given
        Wallet wallet = new Wallet(BigDecimal.ONE, Currency.PLN);
        Wallet wallet2 = new Wallet(BigDecimal.ZERO, Currency.EUR);

        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet2);

        //When
        Wallet testWallet = walletService.initWallet(wallet);

        //Then
        assertEquals(wallet2.getId(), testWallet.getId());
        assertEquals(wallet2.getAccountBalance(), testWallet.getAccountBalance());
        assertEquals(wallet2.getCurrency(), testWallet.getCurrency());
    }

    @Test
    public void testGetWalletById() {
        //Given
        Wallet wallet2 = new Wallet(BigDecimal.ZERO, Currency.EUR);

        when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet2));

        //When
        Wallet testWallet = walletService.getWalletById(2L);

        //Then
        assertEquals(wallet2.getId(), testWallet.getId());
        assertEquals(wallet2.getAccountBalance(), testWallet.getAccountBalance());
        assertEquals(wallet2.getCurrency(), testWallet.getCurrency());
    }

    @Test
    public void testAddFunds() {
        //Given
        Wallet wallet = new Wallet(BigDecimal.ONE, Currency.PLN);
        Wallet wallet2 = new Wallet(BigDecimal.ZERO, Currency.EUR);

        when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet));
        when(walletService.addFunds(anyLong(),BigDecimal.ZERO)).thenReturn(wallet2);

        //When
        Wallet testWallet = walletService.addFunds(2L, BigDecimal.ONE);

        //Then
        assertEquals(wallet2.getId(), testWallet.getId());
        assertEquals(wallet2.getAccountBalance(), testWallet.getAccountBalance());
        assertEquals(wallet2.getCurrency(), testWallet.getCurrency());
    }

    @Test
    public void testDeductFunds() {
        //Given
        Wallet wallet = new Wallet(BigDecimal.ONE, Currency.PLN);
        Wallet wallet2 = new Wallet(BigDecimal.ZERO, Currency.EUR);

        when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet));
        when(walletService.deductFunds(anyLong(),BigDecimal.ZERO)).thenReturn(wallet2);

        //When
        Wallet testWallet = walletService.deductFunds(2L, BigDecimal.ONE);

        //Then
        assertEquals(wallet2.getId(), testWallet.getId());
        assertEquals(wallet2.getAccountBalance(), testWallet.getAccountBalance());
        assertEquals(wallet2.getCurrency(), testWallet.getCurrency());
    }

    @Test
    public void testChangeCurrency() {
        //Given
        Wallet wallet = new Wallet(BigDecimal.ONE, Currency.PLN);
        Wallet wallet2 = new Wallet(BigDecimal.ZERO, Currency.EUR);

        when(walletRepository.findById(anyLong())).thenReturn(Optional.of(wallet));
        when(walletService.changeCurrency(anyLong(),Currency.PLN)).thenReturn(wallet2);

        //When
        Wallet testWallet = walletService.changeCurrency(2L, Currency.PLN);

        //Then
        assertEquals(wallet2.getId(), testWallet.getId());
        assertEquals(wallet2.getAccountBalance(), testWallet.getAccountBalance());
        assertEquals(wallet2.getCurrency(), testWallet.getCurrency());
    }


}