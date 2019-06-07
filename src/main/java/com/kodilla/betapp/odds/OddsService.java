package com.kodilla.betapp.odds;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OddsService implements OddsServiceInterface {
    private final OddsRepository oddsRepository;

    public Odds getOddsById(long id) {
        return oddsRepository.findById(id).orElseThrow(() -> new OddsNotFoundException("Odds with given id" + id + " doesn't exist"));
    }

    @Override
    public Odds addOdds(Odds odds) {
        return oddsRepository.save(odds);
    }
}
