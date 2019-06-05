package com.kodilla.betapp.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Override
    Event save(Event event);

    @Override
    Optional<Event> findById(Long id);

    @Override
    List<Event> findAll();
}
