package com.kodilla.betapp.event;

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
@RequestMapping("/events")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EventController {
    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    Long addEvent(@RequestBody EventDto eventDto) {
        log.info("Add event called. EventDto [{}]", eventDto);

        Event event = eventService.addEvent(eventMapper.mapToEvent(eventDto));
        return event.getId();
    }

    @PatchMapping("/{id}")
    EventDto updateEvent(@PathVariable long id) {
        log.info("Update event with id [{}] ", id);

        return eventMapper.mapToEventDto(eventService.updateEvent(id));
    }

    @DeleteMapping("/{id}")
    void deleteEvent(@PathVariable long id) {
        log.info("Delete event with id [{}]", id);

        eventService.deleteEvent(id);
    }

    @GetMapping
    public List<EventDto> getAllEvents() {
        log.info("Get all events called");

        return eventMapper.mapToEventDtoList(eventService.getAllEvents());
    }

}
