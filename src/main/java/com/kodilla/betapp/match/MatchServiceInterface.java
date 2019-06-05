package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Result;

import java.util.List;

public interface MatchServiceInterface {
    Match addMatch(Match match);
    Match updateResult(Result result, Long id);
    void deleteMatch(Long id);
    List<Match> listOfAllMatches();
}
