package com.fyp.SpringSophie2.model;

import java.time.LocalDate;

public class EventDTO {

    private String eventID;
    private String eventName;
    private LocalDate eventDate;
    private String eventStatus;
    private Double eventCompletionPercentage;

    //Parameterised Constructors for all attributes
    public EventDTO(String eventID, String eventName, LocalDate eventDate, String eventStatus, Double eventCompletionPercentage) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventStatus = eventStatus;
        this.eventCompletionPercentage = eventCompletionPercentage;
    }

    //Getters and Setters for all attributes

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Double getEventCompletionPercentage() {
        return eventCompletionPercentage;
    }

    public void setEventCompletionPercentage(Double eventCompletionPercentage) {
        this.eventCompletionPercentage = eventCompletionPercentage;
    }
}
