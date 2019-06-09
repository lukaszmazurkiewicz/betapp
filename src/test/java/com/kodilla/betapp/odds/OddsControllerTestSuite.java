package com.kodilla.betapp.odds;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OddsController.class)
public class OddsControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OddsMapper oddsMapper;

    @MockBean
    private OddsService oddsService;

    @Test
    public void testAddOdds() throws Exception {
        //Given
        Odds odds = new Odds(Result.WIN, BigDecimal.ONE);
        OddsDto oddsDto = new OddsDto(Result.DRAW, BigDecimal.ZERO);

        when(oddsService.addOdds(oddsMapper.mapToOdds(oddsDto))).thenReturn(odds);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(oddsDto);

        //When & Then
        mockMvc.perform(post("/odds")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}