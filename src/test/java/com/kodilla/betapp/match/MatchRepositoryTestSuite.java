package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchRepositoryTestSuite {
    @Autowired
    private MatchRepository matchRepository;

    @Transactional
    @Test
    public void testFindAll() {
        //Given
        long sizeOfDatabaseBeforeTest = matchRepository.count();

        Match match = new Match(1L, "Lech", "Legia", LocalDate.of(2019, 04, 20), Result.DRAW);
        Match match2 = new Match(2L, "Lech", "Legia", LocalDate.of(2018, 04, 20), Result.WIN);

        matchRepository.save(match);
        matchRepository.save(match2);

        //When
        List<Match> matches = matchRepository.findAll();

        //Then
        assertEquals(2, matches.size() - sizeOfDatabaseBeforeTest);
        assertTrue(matches.contains(match));
        assertTrue(matches.contains(match2));
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Match match = new Match("Lech", "Legia", LocalDate.of(2019, 04, 20), Result.DRAW);

        matchRepository.save(match);

        //When
        Optional<Match> testMatch = matchRepository.findById(match.getId());

        //Then
        assertTrue(testMatch.isPresent());
        assertEquals(Optional.of(match), testMatch);
        assertEquals("Lech", testMatch.get().getHostTeam());
        assertEquals("Legia", testMatch.get().getGuestTeam());
        assertEquals(LocalDate.of(2019, 04, 20), testMatch.get().getMatchDate());
        assertEquals(Result.DRAW, testMatch.get().getEndResult());
    }

    @Transactional
    @Test
    public void testSave() {
        //Given
        long sizeOfDatabaseBeforeTest = matchRepository.count();

        Match match = new Match(1L, "Lech", "Legia", LocalDate.of(2019, 04, 20), Result.DRAW);
        Match match2 = new Match(2L, "Lech", "Legia", LocalDate.of(2018, 04, 20), Result.WIN);

        //When
        matchRepository.save(match);
        matchRepository.save(match2);

        //Then
        assertEquals(2L, matchRepository.count() - sizeOfDatabaseBeforeTest);
    }
}