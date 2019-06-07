package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchDto {
    private long id;
    private String hostTeam;
    private String guestTeam;
    private LocalDate matchDate;
    private Result endResult;

}
