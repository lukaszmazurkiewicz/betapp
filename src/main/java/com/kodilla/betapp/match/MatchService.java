package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MatchService implements MatchServiceInterface {
    private final MatchRepository matchRepository;

    @Override
    public Match getMatchById(long id) {
        return matchRepository.findById(id).orElseThrow(() -> new MatchNotFindException("Match with id " + id + " not found."));
    }

    @Override
    public Match addMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match updateResult(Result result, long id) {
        Match match = getMatchById(id);
        match.setEndResult(result);

        return matchRepository.save(match);
    }

    @Override
    public void deleteMatch(long id) {
        matchRepository.deleteById(id);
    }

    @Override
    public List<Match> getListOfAllMatches() {
        return matchRepository.findAll();
    }
}
