package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wizyg.payroll.DAO.EmployeeDAO;
import pl.wizyg.payroll.entity.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public List<Employee> getEmployees() {
        return employeeDAO.getEmployees();
    }

    @Override
    public Employee getEmployee(int id) {
        return null;
    }
}