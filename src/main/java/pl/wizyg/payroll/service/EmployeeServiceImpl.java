package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.DAO.EmployeeDAO;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.repository.EmployeeRepository;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getActiveEmployees() {
        List<Employee> employees = employeeRepository.findAllByActiveTrue();
        return employees;
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).get();
    }
}
