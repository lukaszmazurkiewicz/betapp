package com.kodilla.betapp.match;

import com.google.gson.Gson;
import com.kodilla.betapp.odds.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MatchController.class)
public class MatchControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchMapper matchMapper;

    @MockBean
    private MatchService matchService;

    @Test
    public void testAddMatch() throws Exception {
        //Given
        Match match = new Match(1L, "Lech", "Legia", Result.DRAW);
        MatchDto matchDto = new MatchDto(2L, "Lech", "Legia", Result.WIN);

        when(matchService.addMatch(matchMapper.mapToMatch(matchDto))).thenReturn(match);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(matchDto);

        //When & Then
        mockMvc.perform(post("/matches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllMatches() throws Exception {
        //Given
        MatchDto matchDto = new MatchDto(1L, "Lech", "Legia", Result.WIN);
        MatchDto matchDto2 = new MatchDto(2L, "Lech", "Legia", Result.WIN);
        List<MatchDto> matchDtos = new ArrayList<>();
        matchDtos.add(matchDto);
        matchDtos.add(matchDto2);

        when(matchMapper.mapToMatchDtoList(matchService.getListOfAllMatches())).thenReturn(matchDtos);

        //When & Then
        mockMvc.perform(get("/matches").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].hostTeam", is("Lech")))
                .andExpect(jsonPath("$[0].guestTeam", is("Legia")))
                .andExpect(jsonPath("$[0].endResult", is("WIN")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].hostTeam", is("Lech")))
                .andExpect(jsonPath("$[1].guestTeam", is("Legia")))
                .andExpect(jsonPath("$[1].endResult", is("WIN")));
    }

    @Test
    public void testUpdateResult() throws Exception {
        //Given
        long id = 1L;
        Result result = Result.LOST;
        MatchDto matchDto = new MatchDto(1L, "Lech", "Legia", Result.WIN);

        when(matchMapper.mapToMatchDto(matchService.updateResult(result, id))).thenReturn(matchDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(matchDto);

        //When & Then
        mockMvc.perform(patch("/matches/WIN/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.hostTeam", is("Lech")))
                .andExpect(jsonPath("$.guestTeam", is("Legia")))
                .andExpect(jsonPath("$.endResult", is("WIN")));
    }

    @Test
    public void testDeleteMatch() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(delete("/matches/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}