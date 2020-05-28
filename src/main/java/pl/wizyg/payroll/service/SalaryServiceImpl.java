package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wizyg.payroll.entity.EssentialSalary;
import pl.wizyg.payroll.entity.Salary;
import pl.wizyg.payroll.repository.SalaryRepository;

import java.util.List;
@Service
public class SalaryServiceImpl implements SalaryService {

    final
    SickLeaveService sickLeaveService;

    final
    EmployeeService employeeService;

    @Autowired
    SalaryRepository salaryRepository;

    public SalaryServiceImpl(SickLeaveService sickLeaveService, EmployeeService employeeService) {
        this.sickLeaveService = sickLeaveService;
        this.employeeService = employeeService;
    }

    @Override
    public void calculateSalary(int employeeId, int month, int year) {
        Salary salary;
        salary = new EssentialSalary(employeeService.getEmployee(employeeId),
                sickLeaveService.getEmployeesSickLeavesMonthYear(employeeId, month, year),
        sickLeaveService.getEmployeesSickLeavesUpToMonthInYear(employeeId, month, year));
        salary.performCalculations();
        saveSalary(salary);
    }

    @Override
    public void calculateSalariesForActiveEmployees(int month, int year) {

    }

    @Override
    public void calculateOvertimeSalaryForActiveEmployees(int month, int year) {

    }

    @Override
    public void saveSalary(Salary salary) {
        salaryRepository.save(salary);
    }

    @Override
    public void saveSalaries(List<Salary> salaries) {

    }


    @Override
    public Salary getEssentialSalary(int employeeId,int month,int year) {
        return salaryRepository.findByEmployee_IdAndMonthAndYear(employeeId,month,year);
    }

    @Override
    public Salary getOvertimeSalary() {
        return null;
    }

    @Override
    public List<Salary> getEssentialSalariesInMonthYear(int month, int year) {
        return null;
    }

    @Override
    public List<Salary> getOvertimeSalariesInMonthYear(int month, int year) {
        return null;
    }
}
