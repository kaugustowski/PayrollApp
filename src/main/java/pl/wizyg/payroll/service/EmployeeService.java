package pl.wizyg.payroll.service;

import com.sun.tools.javac.util.List;
import pl.wizyg.payroll.entity.Employee;

public interface EmployeeService {
    List<Employee> getEmployees();

    void saveEmployee(Employee employee);


}
