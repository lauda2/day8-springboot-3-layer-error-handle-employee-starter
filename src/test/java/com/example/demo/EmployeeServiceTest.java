package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.exception.InvalidEmployeeException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    private static Employee employee(String name, int age, String gender, double salary) {
        Employee e = new Employee();
        e.setName(name);
        e.setAge(age);
        e.setGender(gender);
        e.setSalary(salary);
        return e;
    }

    private static Employee johnSmith() {
        return employee("John Smith", 28, "MALE", 60000.0);
    }

    private static Employee janeDoe() {
        return employee("Jane Doe", 22, "FEMALE", 60000.0);
    }

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void should_return_employee_when_create_employee() {
        Employee employee = johnSmith();
        when(employeeRepository.createEmployee(any(Employee.class))).thenReturn(employee);
        Employee createdEmployee = employeeService.createEmployee(employee);
        assertEquals(createdEmployee.getAge(), employee.getAge());
    }

    @Test
    public void should_throw_exception_when_create_employee_of_greater_than_65_or_less_than_18() throws InvalidEmployeeException {
        Employee employee = johnSmith();
        employee.setAge(16);
        assertThrows(InvalidEmployeeException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    public void should_throw_exception_when_employee_over_30_and_salary_lower_20000() throws InvalidEmployeeException {
        Employee employee = johnSmith();
        employee.setAge(31);
        employee.setSalary(10000);
        assertThrows(InvalidEmployeeException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    public void should_return_active_employee_when_create_employee() {
        Employee employee = johnSmith();
        employeeService.createEmployee(employee);
        when(employeeRepository.createEmployee(any(Employee.class))).thenReturn(employee);
        assertTrue(employee.isActive());
    }

//    @Test
//    public void should_return_inactive_employee_when_delete_employee() {
//        Employee employee = johnSmith();
//        employee.setId(1);
//        when(employeeRepository.createEmployee(any(Employee.class))).thenReturn(employee);
//        when(employeeRepository.deleteEmployee(anyInt()))
//        employeeService.createEmployee(employee);
//        employeeService.deleteEmployee(1);
//        assertFalse(employee.isActive());
//
//        // assert employeeRepository toBeCalled
//        expect()
//    }



}
