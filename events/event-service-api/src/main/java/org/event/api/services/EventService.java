package org.event.api.services;

import java.util.List;
import org.event.dto.events.Event;

public interface EventService {
    Event createEvent(Event event);

    Event updateEvent(Long id, Event event);

    Event getEvent(Long id);

    void deleteEvent(Long id);

    List<Event> getAllEvents();

    List<Event> getAllEventsByTitle(String title);
}
