package com.fyp.SpringSophie2.Repository;

import com.fyp.SpringSophie2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByUsernameAndPassword(String username, String password);
}
