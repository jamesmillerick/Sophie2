package com.fyp.SpringSophie2.Repository;

import com.fyp.SpringSophie2.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
/*
Similar to that of the EmployeeRepository.java file that was suggested by ChatGPT, because repositories in Spring Data JPA
provide a straightforward way to connect with your database. Also is to be ideal for CRUD operations.
 */
