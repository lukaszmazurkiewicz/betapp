package com.kodilla.betapp.odds;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/odds")
@Slf4j
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class OddsController {
    private final OddsMapper oddsMapper;
    private final OddsService oddsService;

    @PostMapping
    long addOdds(@RequestBody OddsDto oddsDto) {
        log.info("Add odds called. OddsDto [{}]", oddsDto);

        Odds odds = oddsService.addOdds(oddsMapper.mapToOdds(oddsDto));
        return odds.getId();
    }
}
