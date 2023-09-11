package org.event.rest.controllers;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.event.api.services.EventService;
import org.event.dto.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@Api(tags = "EventEntity Service", description = "Operations related to event management")
public class EventServiceController {

    @Autowired
    private final EventService eventService;

    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ApiOperation(value = "Create an event")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping
    @ApiOperation(value = "Update an event")
    public Event updateEvent(@RequestBody Event event) {
        return eventService.updateEvent(event.getId(),event);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get event", notes = "Returns the event by id.")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an event", notes = "Delete an event from the list.")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all events", notes = "Returns a list of all events.")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/title/{title}")
    @ApiOperation(value = "Get all events", notes = "Returns all events.")
    public List<Event> getAllEventsByTitle(@PathVariable String title) {
        return eventService.getAllEventsByTitle(title);
    }

}
