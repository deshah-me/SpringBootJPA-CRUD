package com.example.JPACRUD.controller;

import com.example.JPACRUD.entities.Employee;
import com.example.JPACRUD.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee saveEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmployee) {
        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee.isPresent()) {
            Employee employeeToUpdate = existingEmployee.get();
            employeeToUpdate.setId(updateEmployee.getId());
            employeeToUpdate.setFirstName(updateEmployee.getFirstName());
            employeeToUpdate.setLastName(updateEmployee.getLastName());
            employeeToUpdate.setEmail(employeeToUpdate.getEmail());

            Employee savedEmployee = employeeService.saveEmployee(employeeToUpdate);
            return ResponseEntity.ok(savedEmployee);

        } else {
            //return throw new RuntimeException("Employee Not found in the list");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        try {
            boolean employeeExists = employeeService.getEmployeeById(id).isPresent();

            if (employeeExists) {
                employeeService.deleteEmployeeById(id);
                return ResponseEntity.ok("Employee with ID " + id + " has been deleted.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
