package com.kodilla.betapp.wallet;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
    @Override
    Wallet save(Wallet wallet);

    @Override
    List<Wallet> findAll();

}
