package org.event.api.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.event.api.model.EventEntity;
import org.event.api.repository.EventRepositoryMemory;
import org.event.dto.events.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;
    private final EventRepositoryMemory eventRepository;

    public EventServiceImpl(ModelMapper modelMapper, EventRepositoryMemory eventRepository) {
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        return convertToEvent(eventRepository.save(convertToEventEntity(event)));
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        return convertToEvent(eventRepository.save(convertToEventEntity(event)));
    }

    @Override
    public Event getEvent(Long id) {
        Optional<EventEntity> optionalEventEntity = eventRepository.findById(id);
        if(optionalEventEntity.isPresent()){
            return convertToEvent(optionalEventEntity.get());
        }
        throw new RuntimeException(String.format("Id %s Not Found", id));
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        List<EventEntity> eventEntityList = eventRepository.findAll();
        if(eventEntityList.isEmpty()) {
            return List.of();
        }
        return eventEntityList.stream().map(this ::convertToEvent).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        Collection<EventEntity> eventsByTitle = eventRepository.findEventsByTitle(title);
        return eventsByTitle.stream().map(this::convertToEvent).collect(Collectors.toList());
    }

    public Event convertToEvent(EventEntity eventEntity) {
        return modelMapper.map(eventEntity, Event.class);
    }

    public EventEntity convertToEventEntity(Event event) {
        return modelMapper.map(event, EventEntity.class);
    }
}
