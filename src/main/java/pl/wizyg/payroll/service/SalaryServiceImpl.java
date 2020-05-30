package pl.wizyg.payroll.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.EssentialSalary;
import pl.wizyg.payroll.entity.OvertimeSalary;
import pl.wizyg.payroll.entity.Salary;
import pl.wizyg.payroll.repository.OvertimeSalaryRepository;
import pl.wizyg.payroll.repository.SalaryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {

    final
    SickLeaveService sickLeaveService;

    final
    EmployeeService employeeService;

    final
    SalaryRepository salaryRepository;

    final OvertimeSalaryRepository overtimeSalaryRepository;

    public SalaryServiceImpl(SickLeaveService sickLeaveService, EmployeeService employeeService, SalaryRepository salaryRepository, OvertimeSalaryRepository overtimeSalaryRepository) {
        this.sickLeaveService = sickLeaveService;
        this.employeeService = employeeService;
        this.salaryRepository = salaryRepository;
        this.overtimeSalaryRepository = overtimeSalaryRepository;
    }

    @Override
    public Salary calculateSalary(int employeeId, int month, int year) {
        int sickLeaveMonth = month-1;
        int sickLeaveYear = year;
        if(month==1){
            sickLeaveMonth = 12;
            sickLeaveYear=year-1;
        }
        Salary salary
         = new EssentialSalary(employeeService.getEmployee(employeeId), month, year,
                sickLeaveService.getEmployeesSickLeavesMonthYear(employeeId, sickLeaveMonth, sickLeaveYear),
                sickLeaveService.getEmployeesSickLeavesUpToMonthInYear(employeeId, sickLeaveMonth, sickLeaveYear),
                getSalariesFromLast12Months(month,year));
        salary.performCalculations();

        return salary;
    }

    @Override
    public List<Salary> calculateSalariesForActiveEmployees(int month, int year) {
        List<Employee> employees = employeeService.getActiveEmployees();
        List<Salary> salaries = new ArrayList<Salary>();
        for (Employee employee:employees) {
            salaries.add(calculateSalary(employee.getId(), month, year));
        }
        return salaries;
    }

    @Override
    public Salary calculateOvertimeSalary(int employeeId, int month, int year){

        Salary salary
                = new OvertimeSalary(employeeService.getEmployee(employeeId), month, year);

        salary.performCalculations();

        return salary;
    }

    @Override
    public List<Salary> calculateOvertimeSalaryForActiveEmployees(int month, int year) {
        List<Employee> employees = employeeService.getActiveEmployees();
        List<Salary> salaries = new ArrayList<Salary>();
        for (Employee employee:employees) {
            salaries.add(calculateOvertimeSalary(employee.getId(), month, year));
        }
        return salaries;
    }

    @Override
    public void saveSalary(Salary salary) {
        salaryRepository.save(salary);
    }

    @Override
    public void saveSalaries(List<Salary> salaries) {
        salaryRepository.saveAll(salaries);
    }

    @Override
    public Salary getEssentialSalary(int employeeId,int month,int year) {
        return salaryRepository.findByEmployee_IdAndMonthAndYear(employeeId,month,year);
    }


    @Override
    public Salary getOvertimeSalary(int id) {
        return overtimeSalaryRepository.findById(id).get();
    }

    @Override
    public List<Salary> getEssentialSalariesInMonthYear(int month, int year) {
        return salaryRepository.findAllByMonthAndYear(month,year);
    }

    @Override
    public List<OvertimeSalary> getOvertimeSalariesInMonthYear(int month, int year) {
        return overtimeSalaryRepository.findAllByMonthAndYear(month, year);
    }

    @Override
    public List<Salary> getSalariesFromLast12Months(int month, int year) {
        int prevYear=year-1;
        return salaryRepository.findAllByMonthIsLessThanAndYearOrMonthGreaterThanEqualAndYear(month,year,month,prevYear);
    }
}
