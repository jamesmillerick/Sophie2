package com.fyp.SpringSophie2.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EventDTO {

    private String eventID;
    private String eventName;
    private LocalDate eventDate;
    private String eventStatus;
    /*private Double eventCompletionPercentage;

     */

    //Parameterised Constructors for all attributes
    public EventDTO(String eventID, String eventName, LocalDate eventDate, String eventStatus) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventStatus = eventStatus;
        /*this.eventCompletionPercentage = eventCompletionPercentage;

         */
    }

    //Getters and Setters for all attributes

}
