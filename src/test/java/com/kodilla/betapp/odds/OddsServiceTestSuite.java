package com.kodilla.betapp.odds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OddsServiceTestSuite {
    @InjectMocks
    private OddsService oddsService;

    @Mock
    private OddsRepository oddsRepository;

    @Test
    public void testAddOdds() {
        //Given
        Odds odds = new Odds(Result.LOST, BigDecimal.ONE);
        Odds odds2 = new Odds(Result.DRAW, BigDecimal.ZERO);

        when(oddsRepository.save(any(Odds.class))).thenReturn(odds);

        //When
        Odds testOdds = oddsService.addOdds(odds2);

        //Then
        assertEquals(odds.getId(), testOdds.getId());
        assertEquals(odds.getResult(), testOdds.getResult());
        assertEquals(odds.getMatchOdds(), testOdds.getMatchOdds());
        assertEquals(odds.getMatch(), testOdds.getMatch());
    }

    @Test
    public void testGetOddsById(){
        //Given
        Odds odds = new Odds(Result.LOST, BigDecimal.ONE);

        when(oddsRepository.findById(anyLong())).thenReturn(Optional.of(odds));

        //When
        Odds testOdds = oddsService.getOddsById(1L);

        //Then
        assertEquals(odds.getId(), testOdds.getId());
        assertEquals(odds.getResult(), testOdds.getResult());
        assertEquals(odds.getMatchOdds(), testOdds.getMatchOdds());
        assertEquals(odds.getMatch(), testOdds.getMatch());
    }

    @Test(expected = OddsNotFoundException.class)
    public void testGetOddsByIdThrewException(){
        //Given
        Odds odds = new Odds(Result.LOST, BigDecimal.ONE);

        //When
        Odds testOdds = oddsService.getOddsById(11L);

        //Then
    }

}