package com.kodilla.betapp.match;

import com.kodilla.betapp.odds.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/matches")
@CrossOrigin(origins = "*")
public class MatchController {
    private final MatchMapper matchMapper;
    private final MatchService matchService;

    @PostMapping
    Long addMatch(@RequestBody MatchDto matchDto) {
        log.info("Add match called. MatchDto [{}]", matchDto);

        Match match = matchService.addMatch(matchMapper.mapToMatch(matchDto));
        return match.getId();
    }

    @GetMapping
    public List<MatchDto> getMatches() {
        log.info("List of all matches called.");

        return matchMapper.mapToMatchDtoList(matchService.listOfAllMatches());

    }

    @PatchMapping("/{result}/{id}")
    MatchDto updateResult(@PathVariable Result result, @PathVariable Long id) {
        log.info("Update result of a match.");

        return matchMapper.mapToMatchDto(matchService.updateResult(result, id));
    }

    @DeleteMapping("/{id}")
    void deleteResult(@PathVariable Long id) {
        log.info("Delete of a match.");

        matchService.deleteMatch(id);
    }

}
