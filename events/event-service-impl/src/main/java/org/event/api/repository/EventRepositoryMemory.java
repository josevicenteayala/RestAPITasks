package org.event.api.repository;

import org.event.api.model.EventEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EventRepositoryMemory {

    private static final long EVENT_ID = 123L;
    private static final String TITLE = "Title";
    private static final String PLACE = "Place";
    private static final String SPEAKER = "Speaker";
    private static final String EVENT_TYPE = "eventType";

    private List<EventEntity> eventEntityList;

    public EventRepositoryMemory() {
        eventEntityList = new ArrayList<>();
        eventEntityList.add(createModelEvent());
    }


    public EventEntity save(EventEntity eventEntity) {
        Optional<EventEntity> optionalEventEntity = findById(eventEntity.getId());
        if (optionalEventEntity.isEmpty()) {
            eventEntityList.add(eventEntity);
        }else {
            deleteById(eventEntity.getId());
            eventEntityList.add(eventEntity);
        }
        return eventEntity;
    }

    public Optional<EventEntity> findById(Long id) {
        return eventEntityList
                .stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    public void deleteById(Long id) {
        Optional<EventEntity> optionalEventEntity = findById(id);
        if(optionalEventEntity.isPresent()) {
            eventEntityList.remove(optionalEventEntity.get());
        }
    }

    public List<EventEntity> findAll() {
        return eventEntityList;
    }

    public Collection<EventEntity> findEventsByTitle(String title) {
        return eventEntityList
                .stream()
                .filter(e -> e.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    private static EventEntity createModelEvent() {
        return new EventEntity(EVENT_ID, TITLE, PLACE, SPEAKER, EVENT_TYPE, LocalDateTime.now());
    }
}
