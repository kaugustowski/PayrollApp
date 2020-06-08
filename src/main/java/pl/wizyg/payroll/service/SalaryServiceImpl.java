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

    final
    OvertimeSalaryRepository overtimeSalaryRepository;

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
                sickLeaveService.getEmployeeSickLeavesMonthYear(employeeId, sickLeaveMonth, sickLeaveYear),
                sickLeaveService.getEmployeeSickLeavesUpToMonthInYear(employeeId, sickLeaveMonth, sickLeaveYear),
                getEmployeeSalariesFromPrevious12Months(employeeId,month,year));
        salary.performCalculations();

        return salary;
    }

    @Override
    public List<Salary> calculateSalariesForActiveEmployees(int month, int year) {
        List<Employee> employees = employeeService.getActiveEmployees();
        List<Salary> salaries = new ArrayList<Salary>();
        for (Employee employee:employees) {
            Salary salary = calculateSalary(employee.getId(), month, year);
           // employee.addSalary(salary);
            salaries.add(salary);
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
    public List<Salary> getSalariesForActiveEmployeesInMonthYear(int month, int year) {
        return salaryRepository.findAllByMonthAndYearAndEmployee_ActiveTrue(month, year);
    }

    @Override
    public void saveSalary(Salary salary, int employeeId) {
        employeeService.getEmployee(employeeId).addSalary(salary);
        salaryRepository.save(salary);
    }

    @Override
    public void saveSalaries(List<Salary> salaries) {
        salaryRepository.saveAll(salaries);
    }

    @Override
    public Salary getEmployeeEssentialSalary(int employeeId, int month, int year) {
        return salaryRepository.findByEmployee_IdAndMonthAndYear(employeeId,month,year);
    }

    @Override
    public Salary getEssentialSalary(int id) {
        return salaryRepository.findById(id).get();
    }

    @Override
    public OvertimeSalary getEmployeeOvertimeSalary(int employeeId,int month,int year) {
        return overtimeSalaryRepository.findByEmployee_IdAndMonthAndYear(employeeId,month,year);
    }

    @Override
    public OvertimeSalary getOvertimeSalary(int id) {
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
    public List<Salary> getEmployeeSalariesFromPrevious12Months(int employeeId, int month, int year) {
        int prevYear=year-1;
        return salaryRepository.findAllByEmployeeIdAndMonthIsLessThanAndYearOrMonthGreaterThanEqualAndYear(employeeId,month ,year,month,prevYear);
    }


}
