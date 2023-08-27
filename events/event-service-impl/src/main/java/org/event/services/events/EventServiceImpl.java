package org.event.services.events;

import org.event.dto.events.Event;
import org.event.api.services.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {
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
        return null;
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return null;
    }
}
