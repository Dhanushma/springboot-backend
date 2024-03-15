package com.example.sb.rest.springbootbackend.controller;

import com.example.sb.rest.springbootbackend.exception.ResourceNotFoundException;
import com.example.sb.rest.springbootbackend.model.Employee;
import com.example.sb.rest.springbootbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee resultEmployee =  employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return ResponseEntity.ok(resultEmployee);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee emp) {
        return employeeRepository.save(emp);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee emp) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist"));
        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        employee.setEmailId(emp.getEmailId());
        employeeRepository.save(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist"));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
