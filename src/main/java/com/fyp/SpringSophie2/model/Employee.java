package com.fyp.SpringSophie2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Employee {

    // Getters and setters for all attributes
    // Mark the username as the primary key, and enforce uniqueness and non-null constraints
    @Id
    //@Column annotation acquired from ChatGPT - Query was how to enable username as the primary key for the database table?
    @Column(unique = true, nullable = false) //allows for uniqueness for username and no duplicates
    private String username;// Username is the primary key

    private String firstName;
    private String lastName;
    private String role;
    private String password;

    // Define the One-to-Many relationship with Task
    @OneToMany(mappedBy = "assignedEmployee", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();  // Initialize as empty list to avoid null reference issues


    //Parameterised Constructors
    public Employee(String username, String firstName, String lastName, String role, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
    }

    public Employee() {

    }

}