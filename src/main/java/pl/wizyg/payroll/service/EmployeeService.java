package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    List<Employee> getActiveEmployees();

    Employee getEmployee(int id);

    Employee getEmployee(String email);

    void createEmployeeAccountIfDoesNotExist(Employee employee);

}
