package pl.wizyg.payroll.DAO;

import pl.wizyg.payroll.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getEmployees();

    void saveEmployee();

    Employee getEmployee(int id);

    void deleteEmployee(int id);
}
