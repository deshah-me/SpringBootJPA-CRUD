package com.example.JPACRUD.service;

import com.example.JPACRUD.entities.Employee;
import com.example.JPACRUD.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){//This is a constructor
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployee(){
        return this.employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return this.employeeRepository.findById(id);
    }

public Employee saveEmployee(Employee employee){
      return this.employeeRepository.save(employee);
}


    public void deleteEmployeeById(Long id){
        this.employeeRepository.deleteById(id);
    }
}
