package com.example.JPACRUD.repository;

import com.example.JPACRUD.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
