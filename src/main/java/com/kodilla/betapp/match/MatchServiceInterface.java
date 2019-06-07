package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Result;

import java.util.List;

public interface MatchServiceInterface {
    Match addMatch(Match match);
    Match updateResult(Result result, long id);
    void deleteMatch(long id);
    List<Match> getListOfAllMatches();
    Match getMatchById(long id);
}
