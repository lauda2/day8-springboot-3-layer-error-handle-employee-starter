package com.example.demo.dto;

import com.example.demo.entity.Employee;

import java.util.List;

public class CompanyResponse {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    private List<Employee> employees;
}
