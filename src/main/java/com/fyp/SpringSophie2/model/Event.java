package com.fyp.SpringSophie2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Event {
    // Getters and setters for all attributes
    @Id
    private String eventID;

    private String eventName;
    private LocalDate eventDate;
    private String eventStatus;


    //Parameterised Constructors
    public Event(String eventID, String eventName, LocalDate eventDate, String eventStatus) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventStatus = eventStatus;
    }

    public Event() {
    }
}