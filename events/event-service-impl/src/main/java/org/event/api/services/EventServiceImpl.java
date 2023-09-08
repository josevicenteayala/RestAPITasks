package org.event.api.services;

import java.util.List;
import java.util.stream.Collectors;
import org.event.api.model.EventEntity;
import org.event.api.repository.EventRepository;
import org.event.dto.events.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;

    public EventServiceImpl(ModelMapper modelMapper, EventRepository eventRepository) {
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        return null;
    }

    @Override
    public Event getEvent(Long id) {
        return null;
    }

    @Override
    public void deleteEvent(Long id) {

    }

    @Override
    public List<Event> getAllEvents() {
        List<EventEntity> eventEntityList = eventRepository.findAll();
        return eventEntityList.stream().map(this ::convertToEvent).collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return null;
    }

    public Event convertToEvent(EventEntity eventEntity) {
        return modelMapper.map(eventEntity, Event.class);
    }
}
