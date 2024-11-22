package com.fyp.SpringSophie2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/*
Lombok was suggested by a colleague in my year due to its ability to help reduce boilerplate code and improve development of my project
 */

@Setter
@Getter
@Entity
public class Task {
    // Getters and setters for all attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskID;
    private String taskDescription;
    private LocalDate dueDate;
    private String eventStatus;
    private String eventID;

    @ManyToOne // Many tasks can be assigned to one employee
    @JoinColumn(name = "username", nullable = false) // username is the foreign key in the Task table
    private Employee assignedEmployee;//assignedEmployee is also a foreign key - to the employee table



    public Task(int taskID, String taskDescription, LocalDate dueDate, String eventStatus, String eventID) {
        this.taskID = taskID;
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
        this.eventStatus = eventStatus;
        this.eventID = eventID;
    }
    public Task() {

    }

}
