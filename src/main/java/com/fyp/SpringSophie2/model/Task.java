package com.fyp.SpringSophie2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private Long taskId;

    private String taskName;
    private String taskDescription;
    private String taskStatus;

    @ManyToOne // Many tasks can be assigned to one employee
    @JoinColumn(name = "assigned_employee_username") // username is the foreign key in the Task table
    private Employee assignedEmployee;//assignedEmployee is also a foreign key - to the employee table

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Task() {

    }

    public Task(String taskName, String taskDescription, String taskStatus, Employee assignedEmployee, Event event) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.assignedEmployee = assignedEmployee;
        this.event = event;
    }


}
