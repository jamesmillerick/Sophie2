package com.fyp.SpringSophie2.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskID;
    private String taskName;
    private LocalDate dueDate;
    private String eventStatus;
    private String eventID;

    @ManyToOne // Many tasks can be assigned to one employee
    @JoinColumn(name = "username", nullable = false) // username is the foreign key in the Task table
    private Employee assignedEmployee;//assignedEmployee is also a foreign key - to the employee table



    public Task(int taskID, String taskName, LocalDate dueDate, String eventStatus, String eventID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.eventStatus = eventStatus;
        this.eventID = eventID;
    }
    public Task() {

    }

    // Getters and setters for all attributes
    public int getTaskID() { return taskID; }
    public void setTaskID(int taskID) { this.taskID = taskID; }
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public Employee getAssignedEmployee() { return assignedEmployee; }
    public void setAssignedEmployee(Employee assignedEmployee) { this.assignedEmployee = assignedEmployee; }
    public LocalDate getDueDate() {return dueDate;}
    public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}
    public String getEventStatus() {return eventStatus;}
    public void setEventStatus(String eventStatus) {this.eventStatus = eventStatus;}
    public String getEventID() {return eventID;}
    public void setEventID(String eventID) {this.eventID = eventID;}
}
