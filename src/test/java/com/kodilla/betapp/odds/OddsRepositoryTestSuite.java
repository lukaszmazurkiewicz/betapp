package com.kodilla.betapp.odds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OddsRepositoryTestSuite {
    @Autowired
    private OddsRepository oddsRepository;

    @Transactional
    @Test
    public void testFindAll() {
        //Given
        long sizeOfDatabaseBeforeTest = oddsRepository.count();

        Odds odds = new Odds(Result.DRAW, BigDecimal.TEN);
        Odds odds2 = new Odds(Result.LOST, BigDecimal.TEN);

        oddsRepository.save(odds);
        oddsRepository.save(odds2);

        //When
        List<Odds> oddss = oddsRepository.findAll();

        //Then
        assertEquals(2, oddss.size() - sizeOfDatabaseBeforeTest);
        assertTrue(oddss.contains(odds));
        assertTrue(oddss.contains(odds2));
    }

    @Transactional
    @Test
    public void testFindById() {
        //Given
        Odds odds = new Odds(Result.DRAW, BigDecimal.TEN);

        oddsRepository.save(odds);

        //When
        Optional<Odds> testOdds = oddsRepository.findById(odds.getId());

        //Then
        assertTrue(testOdds.isPresent());
        assertEquals(Optional.of(odds), testOdds);
        assertEquals(Result.DRAW, testOdds.get().getResult());
        assertEquals(BigDecimal.TEN, testOdds.get().getMatchOdds());
    }

    @Transactional
    @Test
    public void testSave() {
        //Given
        long sizeOfDatabaseBeforeTest = oddsRepository.count();

        Odds odds = new Odds(Result.DRAW, BigDecimal.TEN);
        Odds odds2 = new Odds(Result.LOST, BigDecimal.TEN);

        //When
        oddsRepository.save(odds);
        oddsRepository.save(odds2);

        //Then
        assertEquals(2L, oddsRepository.count() - sizeOfDatabaseBeforeTest);
    }
}