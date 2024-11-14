package com.fyp.SpringSophie2.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {

    // Mark the username as the primary key, and enforce uniqueness and non-null constraints
    @Id
    //@Column annotation acquired from ChatGPT - Query was how to enable username as the primary key for the database table?
    @Column(unique = true, nullable = false) //allows for uniqueness for username and no duplicates
    private String username;// Username is the primary key

    private String fName;
    private String sName;
    private String role;
    private String password;

    // Define the One-to-Many relationship with Task
    @OneToMany(mappedBy = "assignedEmployee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;





    //Parameterised Constructors
    public Employee(String username, String fName, String sName, String role, String password) {
        this.username = username;
        this.fName = fName;
        this.sName = sName;
        this.role = role;
        this.password = password;
    }

    public Employee() {

    }

    // Getters and setters for all attributes
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getfName() { return fName; }
    public void setfName(String fName) { this.fName = fName; }
    public String getsName() { return sName; }
    public void setsName(String sName) { this.sName = sName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

}