package pl.wizyg.payroll.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.repository.EmployeeRepository;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getActiveEmployees() {
        return employeeRepository.findAllByActiveTrue();
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).get();
    }
}
