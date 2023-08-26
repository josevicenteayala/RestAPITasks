package org.rest.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.api.services.EventService;
import org.dto.events.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Api(tags = "Event Service", description = "Operations related to event management")
public class EventServiceController {

    private final EventService eventService;

    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @ApiOperation(value = "Get all events", notes = "Returns a list of all events.")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

}
