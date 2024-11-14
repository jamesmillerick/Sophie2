package com.fyp.SpringSophie2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Event {
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


    // Getters and setters for all attributes
    public String getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

}