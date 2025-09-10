package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(String gender, Integer page, Integer size) {
        return employeeRepository.getEmployees(gender, page, size);
    }

    public Employee getEmployeeById(int id) {
        if (employeeRepository.getEmployeeById(id) != null) {
            return employeeRepository.getEmployeeById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.createEmployee(employee);
    }

}
