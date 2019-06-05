package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchDto {
    private long id;
    private String hostTeam;
    private String guestTeam;
    private LocalDate matchDate;
    private Result endResult;

}
