package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    //    void saveEmployee(Employee employee);
//
    Employee getEmployee(int id);
//
//    void deleteTeacher(int id);

}
