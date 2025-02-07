package com.fyp.SpringSophie2.Repository;

import com.fyp.SpringSophie2.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findByEventDateBetween(LocalDate startDate, LocalDate endDate);
}
