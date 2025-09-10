package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(String gender, Integer page, Integer size) {
        Stream<Employee> stream = employeeRepository.getEmployees().stream();
        if (gender != null) {
            stream = stream.filter(employee -> employee.getGender().compareToIgnoreCase(gender) == 0);
        }
        if (page != null && size != null) {
            stream = stream.skip((long) (page - 1) * size).limit(size);
        }
        return stream.toList();
    }

    public Employee getEmployeeById(int id) {
        if (employeeRepository.getEmployeeById(id) != null) {
            return employeeRepository.getEmployeeById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getAge() == null) {
            throw new InvalidAgeEmployeeException("employee age is null");
        } else if (employee.getAge() > 65 || employee.getAge() < 18) {
            throw new InvalidAgeEmployeeException("employee age is less than 18 or greater than 65!");
        } else if (employee.getAge() > 30 && employee.getSalary() < 20000) {
            throw new InvalidAgeEmployeeException("employee salary is less than 20000!");
        }
        return employeeRepository.createEmployee(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        return employeeRepository.updateEmployee(id, updatedEmployee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployee(id);
    }

    public void empty() {
        employeeRepository.empty();
    }

}
