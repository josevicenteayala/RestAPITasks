package org.event.rest.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.event.api.model.EventEntity;
import org.event.api.repository.EventRepositoryMemory;
import org.event.api.services.EventService;
import org.event.dto.events.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EventServiceControllerTest {

    public static final long EVENT_ID = 123L;
    public static final String TITLE = "Title";
    public static final String PLACE = "Place";
    public static final String SPEAKER = "Speaker";
    public static final String EVENT_TYPE = "eventType";
    public static final String TITLE_MODIFIED = "Title Modified";
    public static final String SPEAKER_MODIFIED = "Speaker Modified";
    public static final String EVENT_TYPE_MODIFIED = "EventType Modified";
    public static final String PLACE_MODIFIED = "Place Modified";
    public static final long ID_SECOND_EVENT = 124L;

    @Autowired
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventRepositoryMemory eventRepository;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new EventServiceController(eventService)).build();
    }

    @Test
    void getAllEvents() throws Exception {
        Event eventExpected = getEvent();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(eventExpected.getId()))
                .andExpect(jsonPath("$[0].title").value(eventExpected.getTitle()))
                .andExpect(jsonPath("$[0].place").value(eventExpected.getPlace()))
                .andExpect(jsonPath("$[0].speaker").value(eventExpected.getSpeaker()))
                .andExpect(jsonPath("$[0].eventType").value(eventExpected.getEventType()))
                .andReturn();
    }

    @Test
    void getEventById() throws Exception {
        Event eventExpected = getEvent();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/events/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(eventExpected.getId()))
                .andExpect(jsonPath("$.title").value(eventExpected.getTitle()))
                .andExpect(jsonPath("$.place").value(eventExpected.getPlace()))
                .andExpect(jsonPath("$.speaker").value(eventExpected.getSpeaker()))
                .andExpect(jsonPath("$.eventType").value(eventExpected.getEventType()))
                .andReturn();
    }

    @Test
    void getEventByTitle() throws Exception {
        Event eventExpected = getEvent();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/events/title/Title")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(eventExpected.getId()))
                .andExpect(jsonPath("$[0].title").value(eventExpected.getTitle()))
                .andExpect(jsonPath("$[0].place").value(eventExpected.getPlace()))
                .andExpect(jsonPath("$[0].speaker").value(eventExpected.getSpeaker()))
                .andExpect(jsonPath("$[0].eventType").value(eventExpected.getEventType()))
                .andReturn();
    }

    @Test
    void testCreateEvent() throws Exception {
        String eventJson = objectMapper.writeValueAsString(getEvent());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eventJson)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(EVENT_ID))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.place").value(PLACE))
                .andExpect(jsonPath("$.speaker").value(SPEAKER))
                .andExpect(jsonPath("$.eventType").value(EVENT_TYPE))
                .andReturn();
    }

    @Test
    void testCreateUpdateDeleteGetAll() throws Exception {

        //Create event 124
        Event event = getEvent();
        event.setId(ID_SECOND_EVENT);
        String eventJson = objectMapper.writeValueAsString(event);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eventJson)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ID_SECOND_EVENT))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.place").value(PLACE))
                .andExpect(jsonPath("$.speaker").value(SPEAKER))
                .andExpect(jsonPath("$.eventType").value(EVENT_TYPE))
                .andReturn();

        //Get all current events
        requestBuilder = MockMvcRequestBuilders.get("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(EVENT_ID))
                .andExpect(jsonPath("$[0].title").value(TITLE))
                .andExpect(jsonPath("$[0].place").value(PLACE))
                .andExpect(jsonPath("$[0].speaker").value(SPEAKER))
                .andExpect(jsonPath("$[0].eventType").value(EVENT_TYPE))
                .andExpect(jsonPath("$[1].id").value(ID_SECOND_EVENT))
                .andExpect(jsonPath("$[1].title").value(TITLE))
                .andExpect(jsonPath("$[1].place").value(PLACE))
                .andExpect(jsonPath("$[1].speaker").value(SPEAKER))
                .andExpect(jsonPath("$[1].eventType").value(EVENT_TYPE))
                .andReturn();

        //Get Event 123 (Default Event in memory)
        requestBuilder = MockMvcRequestBuilders.get("/api/events/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(EVENT_ID))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.place").value(PLACE))
                .andExpect(jsonPath("$.speaker").value(SPEAKER))
                .andExpect(jsonPath("$.eventType").value(EVENT_TYPE))
                .andReturn();

        //Update Event 123
        Event eventToUpdate = getEvent();
        eventToUpdate.setId(ID_SECOND_EVENT);
        eventToUpdate.setTitle(TITLE_MODIFIED);
        eventToUpdate.setSpeaker(SPEAKER_MODIFIED);
        eventToUpdate.setEventType(EVENT_TYPE_MODIFIED);
        eventToUpdate.setPlace(PLACE_MODIFIED);
        String eventJsonToUpdate = objectMapper.writeValueAsString(eventToUpdate);

        requestBuilder = MockMvcRequestBuilders.put("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eventJsonToUpdate)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(ID_SECOND_EVENT))
                .andExpect(jsonPath("$.title").value(TITLE_MODIFIED))
                .andExpect(jsonPath("$.place").value(PLACE_MODIFIED))
                .andExpect(jsonPath("$.speaker").value(SPEAKER_MODIFIED))
                .andExpect(jsonPath("$.eventType").value(EVENT_TYPE_MODIFIED))
                .andReturn();

        //Get all current events
        requestBuilder = MockMvcRequestBuilders.get("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(EVENT_ID))
                .andExpect(jsonPath("$[0].title").value(TITLE))
                .andExpect(jsonPath("$[0].place").value(PLACE))
                .andExpect(jsonPath("$[0].speaker").value(SPEAKER))
                .andExpect(jsonPath("$[0].eventType").value(EVENT_TYPE))
                .andExpect(jsonPath("$[1].id").value(ID_SECOND_EVENT))
                .andExpect(jsonPath("$[1].title").value(TITLE_MODIFIED))
                .andExpect(jsonPath("$[1].place").value(PLACE_MODIFIED))
                .andExpect(jsonPath("$[1].speaker").value(SPEAKER_MODIFIED))
                .andExpect(jsonPath("$[1].eventType").value(EVENT_TYPE_MODIFIED))
                .andReturn();

        //Delete Event 123
        requestBuilder = MockMvcRequestBuilders.delete("/api/events/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //Get all current events
        requestBuilder = MockMvcRequestBuilders.get("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(ID_SECOND_EVENT))
                .andExpect(jsonPath("$[0].title").value(TITLE_MODIFIED))
                .andExpect(jsonPath("$[0].place").value(PLACE_MODIFIED))
                .andExpect(jsonPath("$[0].speaker").value(SPEAKER_MODIFIED))
                .andExpect(jsonPath("$[0].eventType").value(EVENT_TYPE_MODIFIED))
                .andReturn();
    }

    private List<Event> createEventList() {
        return List.of(getEvent(), getEvent());
    }

    private static Event getEvent() {
        Event event = new Event();
        event.setId(EVENT_ID);
        event.setPlace(PLACE);
        event.setTitle(TITLE);
        event.setSpeaker(SPEAKER);
        event.setEventType(EVENT_TYPE);
        event.setDateTime(LocalDateTime.now());
        return event;
    }

    private List<EventEntity> createModelEvents() {
        return List.of(createModelEvent(), createModelEvent());
    }

    private static EventEntity createModelEvent() {
        return new EventEntity(EVENT_ID, TITLE, PLACE, SPEAKER, EVENT_TYPE, LocalDateTime.now());
    }
}