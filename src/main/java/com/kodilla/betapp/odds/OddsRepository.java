package com.kodilla.betapp.odds;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OddsRepository extends CrudRepository<Odds, Long> {
    @Override
    Optional<Odds> findById(Long id);

    @Override
    Odds save(Odds odds);

    @Override
    List<Odds> findAll();

    Odds findByResultAndMatchId(Result result, long matchId);
}
