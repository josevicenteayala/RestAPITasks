package org.event.rest.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import org.event.api.repository.EventRepository;
import org.event.api.services.EventService;
import org.event.dto.events.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EventServiceControllerTest {

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new EventServiceController(eventService)).build();
    }

    @Test
    void getAllEvents() throws Exception {

        List<Event> events = createEventList();
        when(eventService.getAllEvents()).thenReturn(events);

        Event eventExpected = getEvent();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].eventType").value(eventExpected.getEventType()))
                .andExpect(jsonPath("$[0].id").value(eventExpected.getId()))
                .andExpect(jsonPath("$[0].title").value(eventExpected.getTitle()))
                .andExpect(jsonPath("$[0].place").value(eventExpected.getPlace()))
                .andExpect(jsonPath("$[0].speaker").value(eventExpected.getSpeaker()))
                .andExpect(jsonPath("$[1].eventType").value(eventExpected.getEventType()))
                .andExpect(jsonPath("$[1].id").value(eventExpected.getId()))
                .andExpect(jsonPath("$[1].title").value(eventExpected.getTitle()))
                .andExpect(jsonPath("$[1].place").value(eventExpected.getPlace()))
                .andExpect(jsonPath("$[1].speaker").value(eventExpected.getSpeaker()))
                .andReturn();

    }

    private List<Event> createEventList() {
        return List.of(getEvent(), getEvent());
    }

    private static Event getEvent() {
        Event event = new Event();
        event.setEventType("EventType");
        event.setId(123L);
        event.setPlace("Place");
        event.setTitle("Title");
        event.setSpeaker("Speaker");
        event.setDateTime(LocalDateTime.now());
        return event;
    }
}