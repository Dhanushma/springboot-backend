package com.example.sb.rest.springbootbackend.repository;

import com.example.sb.rest.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select emp from Employee emp where " +
            "emp.firstName like concat('%',:query,'%') " +
            "or emp.emailId like concat('%',:query,'%')")
    List<Employee> searchEmployee(String query);
}
