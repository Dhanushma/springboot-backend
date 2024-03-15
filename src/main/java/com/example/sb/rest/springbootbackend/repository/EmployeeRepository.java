package com.example.sb.rest.springbootbackend.repository;

import com.example.sb.rest.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
