package com.kodilla.betapp.odds;

import com.kodilla.betapp.match.Match;
import com.kodilla.betapp.match.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OddsMapper {
    private final MatchService matchService;

    Odds mapToOdds(final OddsDto oddsDto) {
        Match match = matchService.getMatchById(oddsDto.getMatchId());

        return new Odds (
                oddsDto.getId(),
                oddsDto.getResult(),
                oddsDto.getMatchOdds(),
                match
        );
    }
}
