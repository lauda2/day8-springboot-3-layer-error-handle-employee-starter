package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.InvalidEmployeeException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.IEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final IEmployeeRepository repository;

    public EmployeeService(EmployeeRepository employeeRepository, IEmployeeRepository repository) {
        this.employeeRepository = employeeRepository;
        this.repository = repository;
    }

    public List<Employee> getEmployees(String gender, Integer page, Integer size) {
        if (gender == null) {
            if (page == null || size == null) {
                return repository.findAll();
            } else {
                Pageable pageable = PageRequest.of(page - 1, size);
                return repository.findAll(pageable).toList();
            }
        } else {
            if (page == null || size == null) {
                return repository.findEmployeesByGender(gender);
            } else {
                Pageable pageable = PageRequest.of(page - 1, size);
                return repository.findEmployeesByGender(gender, pageable);
            }
        }
    }

    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return employee.get();
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getAge() == null) {
            throw new InvalidEmployeeException("employee age is null");
        } else if (employee.getAge() > 65 || employee.getAge() < 18) {
            throw new InvalidEmployeeException("employee age is less than 18 or greater than 65!");
        } else if (employee.getAge() > 30 && employee.getSalary() < 20000) {
            throw new InvalidEmployeeException("employee salary is less than 20000!");
        }
        return employeeRepository.createEmployee(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        if (employeeRepository.getEmployeeById(id).isActive()) {
            return employeeRepository.updateEmployee(id, updatedEmployee);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);
    }

    public void empty() {
        employeeRepository.empty();
    }

}
