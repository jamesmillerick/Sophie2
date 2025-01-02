package com.fyp.SpringSophie2.Repository;

import com.fyp.SpringSophie2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
/*
This was suggested by ChatGPT, because repositories in Spring Data JPA
provide a straightforward way to connect with your database. Also is to be ideal for CRUD operations.
 */