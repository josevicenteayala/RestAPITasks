package org.event.api.repository;

import org.event.api.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query("SELECT e FROM EventEntity e WHERE e.title = ?1")
    Collection<EventEntity> findEventsByTitle(String title);
}
