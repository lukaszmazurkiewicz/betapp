package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTestSuite {
    @InjectMocks
    private MatchService matchService;

    @Mock
    private MatchRepository matchRepository;

    @Test
    public void testAddMatch() {
        //Given
        Match match = new Match(1L, "Lech", "Legia", LocalDate.of(2019, 04, 20), Result.DRAW);
        Match match2 = new Match(2L, "Lech", "Legia", LocalDate.of(2018, 04, 20), Result.WIN);

        when(matchRepository.save(any(Match.class))).thenReturn(match);

        //When
        Match testMatch = matchService.addMatch(match2);

        //Then
        assertEquals(match.getId(), testMatch.getId());
        assertEquals(match.getHostTeam(), testMatch.getHostTeam());
        assertEquals(match.getGuestTeam(), testMatch.getGuestTeam());
        assertEquals(match.getMatchDate(), testMatch.getMatchDate());
        assertEquals(match.getEndResult(), testMatch.getEndResult());
    }

    @Test
    public void testGetMatchById() {
        //Given
        Match match = new Match(1L, "Lech", "Legia", LocalDate.of(2019, 04, 20), Result.DRAW);

        when(matchRepository.findById(anyLong())).thenReturn(Optional.of(match));

        //When
        Match testMatch = matchService.getMatchById(2L);

        //Then
        assertEquals(match.getId(), testMatch.getId());
        assertEquals(match.getHostTeam(), testMatch.getHostTeam());
        assertEquals(match.getGuestTeam(), testMatch.getGuestTeam());
        assertEquals(match.getMatchDate(), testMatch.getMatchDate());
        assertEquals(match.getEndResult(), testMatch.getEndResult());
    }

    @Test(expected = MatchNotFindException.class)
    public void testGetMatchByIdThrewException() {
        //Given
        Match match = new Match(1L, "Lech", "Legia", LocalDate.of(2019, 04, 20), Result.DRAW);

        //When
        Match testMatch = matchService.getMatchById(2L);

        //Then
    }

    @Test
    public void testDeleteMatch() {
        //Given
        long sizeOfDatabaseBeforeTest = matchRepository.count();

        Match match = new Match(1L, "Lech", "Legia", LocalDate.of(2019, 04, 20), Result.DRAW);

        matchService.addMatch(match);

        //When
        matchService.deleteMatch(match.getId());

        //Then
        assertEquals(0, matchRepository.count() - sizeOfDatabaseBeforeTest);
    }

    @Test
    public void testGetListOfAllMatches() {
        //Given
        Match match = new Match(1L, "Lech", "Legia", LocalDate.of(2019, 04, 20), Result.DRAW);
        Match match2 = new Match(2L, "Lech", "Legia", LocalDate.of(2018, 04, 20), Result.WIN);

        matchRepository.save(match);
        matchRepository.save(match2);

        List<Match> matches = new ArrayList<>();
        matches.add(match);
        matches.add(match2);

        when(matchRepository.findAll()).thenReturn(matches);

        //When
        List<Match> testMatchesList = matchService.getListOfAllMatches();

        //Then
        assertEquals(2, testMatchesList.size());
        assertTrue(matches.contains(match));
        assertTrue(matches.contains(match2));
    }
}