package com.kodilla.betapp.match;

import java.util.List;

public interface MatchServiceInterface {
    Match addMatch();
    Match updateResult();
    void deleteMatch();
    List<Match> listOfAllMatches();
}
