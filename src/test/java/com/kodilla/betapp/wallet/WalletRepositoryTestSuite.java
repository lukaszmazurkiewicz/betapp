package com.kodilla.betapp.wallet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletRepositoryTestSuite {
    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void testFindAll() {
        //Given
        long sizeOfDatabaseBeforeTest = walletRepository.count();

        Wallet wallet = new Wallet(new BigDecimal(2).setScale(2), Currency.PLN);
        Wallet wallet2 = new Wallet(new BigDecimal(2).setScale(2), Currency.USD);

        walletRepository.save(wallet);
        walletRepository.save(wallet2);

        //When
        List<Wallet> wallets = walletRepository.findAll();

        //Then
        assertEquals(2, wallets.size() - sizeOfDatabaseBeforeTest);
        assertTrue(wallets.contains(wallet));
        assertTrue(wallets.contains(wallet2));
    }

    @Test
    public void testFindById() {
        //Given
        Wallet wallet = new Wallet(new BigDecimal(2).setScale(2), Currency.PLN);

        walletRepository.save(wallet);

        //When
        Optional<Wallet> testWallet = walletRepository.findById(wallet.getId());

        //Then
        assertTrue(testWallet.isPresent());
        assertEquals(Optional.of(wallet), testWallet);
        assertEquals(new BigDecimal(2).setScale(2), testWallet.get().getAccountBalance());
        assertEquals(Currency.PLN, testWallet.get().getCurrency());
    }

    @Test
    public void testSave() {
        //Given
        long sizeOfDatabaseBeforeTest = walletRepository.count();

        Wallet wallet = new Wallet(new BigDecimal(2).setScale(2), Currency.PLN);
        Wallet wallet2 = new Wallet(new BigDecimal(2).setScale(2), Currency.USD);

        //When
        walletRepository.save(wallet);
        walletRepository.save(wallet2);

        //Then
        assertEquals(2L, walletRepository.count() - sizeOfDatabaseBeforeTest);
    }

}