package com.kodilla.betapp.match;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchMapper {
    Match mapToMatch(final MatchDto matchDto) {
        return new Match(
                matchDto.getId(),
                matchDto.getHostTeam(),
                matchDto.getGuestTeam(),
                matchDto.getMatchDate(),
                matchDto.getEndResult()
        );
    }

    MatchDto mapToMatchDto(final Match match) {
        return new MatchDto(
                match.getId(),
                match.getHostTeam(),
                match.getGuestTeam(),
                match.getMatchDate(),
                match.getEndResult()
        );
    }

    public List<MatchDto> mapToMatchDtoList(final List<Match> matches) {
        return matches.stream()
                .map(m -> mapToMatchDto(m))
                .collect(Collectors.toList());
    }

}
