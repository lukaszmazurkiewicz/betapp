package com.kodilla.betapp.wallet;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, Long> {
    @Override
    Wallet save(Wallet wallet);

    @Override
    List<Wallet> findAll();

    @Override
    Optional<Wallet> findById(Long id);

}
