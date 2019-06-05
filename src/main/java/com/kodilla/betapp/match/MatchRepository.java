package com.kodilla.betapp.match;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    @Override
    List<Match> findAll();

    @Override
    Optional<Match> findById(Long id);

    @Override
    Match save(Match match);

    @Override
    void deleteById(Long id);
}
