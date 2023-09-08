package services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import org.event.api.model.Event;
import org.event.api.repository.EventRepository;
import org.event.api.services.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {org.event.rest.RestApplication.class})
class EventServiceImplTest {

    public static final long EVENT_ID = 123L;
    public static final String TITLE = "Title";
    public static final String PLACE = "Place";
    public static final String SPEAKER = "Speaker";
    public static final String EVENT_TYPE = "eventType";
    @MockBean
    private EventRepository eventRepository;

    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        eventService = new EventServiceImpl(modelMapper, eventRepository);
    }

    @Test
    void createEvent() {
    }

    @Test
    void updateEvent() {
    }

    @Test
    void getEvent() {
    }

    @Test
    void deleteEvent() {
    }

    @Test
    void getAllEvents() {
        List<Event> listEvents = createModelEvents();
        when(eventRepository.findAll()).thenReturn(listEvents);

        List<org.event.dto.events.Event> allEvents = eventService.getAllEvents();
        org.event.dto.events.Event event = allEvents.get(0);
        assertAll(
                () -> assertEquals(EVENT_ID, event.getId()),
                () -> assertEquals(TITLE, event.getTitle()),
                () -> assertEquals(PLACE, event.getPlace()),
                () -> assertEquals(SPEAKER, event.getSpeaker()),
                () -> assertEquals(EVENT_TYPE, event.getEventType()),
                () -> assertInstanceOf(LocalDateTime.class, event.getDateTime())
        );
    }

    @Test
    void getAllEventsByTitle() {
    }

    @Test
    void convertToEvent() {
        org.event.dto.events.Event event = eventService.convertToEvent(createModelEvent());
        assertAll(
                () -> assertEquals(EVENT_ID, event.getId()),
                () -> assertEquals(TITLE, event.getTitle()),
                () -> assertEquals(PLACE, event.getPlace()),
                () -> assertEquals(SPEAKER, event.getSpeaker()),
                () -> assertEquals(EVENT_TYPE, event.getEventType()),
                () -> assertInstanceOf(LocalDateTime.class, event.getDateTime())
        );
    }

    private List<Event> createModelEvents() {
        return List.of(createModelEvent());
    }

    private static Event createModelEvent() {
        return new Event(EVENT_ID, TITLE, PLACE, SPEAKER, EVENT_TYPE, LocalDateTime.now());
    }
}