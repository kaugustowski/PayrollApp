package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    List<Employee> getActiveEmployees();

    //    void saveEmployee(Employee employee);
//
    Employee getEmployee(int id);

    void createEmployeeAccountIfDoesNotExist(Employee employee);

    // void createEmployeeAccount(Teacher employee);
//
//    void deleteTeacher(int id);

}
