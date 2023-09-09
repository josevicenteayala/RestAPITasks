CREATE TABLE EVENTS (
  ID int NOT NULL,
  TITLE varchar(50),
  PLACE varchar(50),
  SPEAKER varchar(50),
  EVENTTYPE varchar(50),
  DATETIME date,
  PRIMARY KEY (ID)
);

INSERT INTO events (id, title, place, speaker, eventType, dateTime) VALUES (1, 'Title', 'Place', 'Speaker', 'EventType','2023-09-08');
INSERT INTO events (id, title, place, speaker, eventType, dateTime) VALUES (2, 'Title2', 'Place2', 'Speaker2', 'EventType2','2023-09-08');
INSERT INTO events (id, title, place, speaker, eventType, dateTime) VALUES (3, 'Title3', 'Place3', 'Speaker3', 'EventType3','2023-09-08');

