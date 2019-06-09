package com.kodilla.betapp.event;

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventMapper eventMapper;

    @MockBean
    private EventService eventService;

    @Test
    public void testAddEvent() throws Exception {
        //Given
        Event event = new Event(Result.DRAW, BigDecimal.ONE, true);
        EventDto eventDto = new EventDto(Result.DRAW, BigDecimal.ONE, false);

        when(eventService.addEvent(eventMapper.mapToEvent(eventDto))).thenReturn(event);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(eventDto);

        //When & Then
        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateEvent() throws Exception {
        //Given
        EventDto eventDto = new EventDto(Result.DRAW, BigDecimal.ONE, false);

        when(eventMapper.mapToEventDto(eventService.updateEvent(anyLong()))).thenReturn(eventDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(eventDto);

        //When & Then
        mockMvc.perform(patch("/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bet", is("DRAW")))
                .andExpect(jsonPath("$.betOdds", is(1)))
                .andExpect(jsonPath("$.win", is(false)));
    }

    @Test
    public void testDeleteEvent() throws Exception {
        //Given

        //When & Then
        mockMvc.perform(delete("/events/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllEvents() throws Exception {
        //Given
        EventDto eventDto = new EventDto(Result.DRAW, BigDecimal.ONE, false);
        EventDto eventDto2 = new EventDto(Result.WIN, BigDecimal.ONE, false);

        List<EventDto> eventDtosList = new ArrayList<>();
        eventDtosList.add(eventDto);
        eventDtosList.add(eventDto2);

        when(eventMapper.mapToEventDtoList(eventService.getAllEvents())).thenReturn(eventDtosList);

        //When & Then
        mockMvc.perform(get("/events/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].bet", is("DRAW")))
                .andExpect(jsonPath("$[0].betOdds", is(1)))
                .andExpect(jsonPath("$[0].win", is(false)))
                .andExpect(jsonPath("$[1].bet", is("WIN")))
                .andExpect(jsonPath("$[1].betOdds", is(1)))
                .andExpect(jsonPath("$[1].win", is(false)));
    }

}