package pl.wizyg.payroll.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.DTO.SalaryListDTO;
import pl.wizyg.payroll.entity.*;
import pl.wizyg.payroll.repository.EssentialSalaryRepository;
import pl.wizyg.payroll.repository.OvertimeSalaryRepository;
import pl.wizyg.payroll.repository.SalaryRepository;

import java.time.LocalDate;
import java.time.YearMonth;
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
    OvertimeService overtimeService;

    final
    SalaryRepository salaryRepository;

    final
    OvertimeSalaryRepository overtimeSalaryRepository;

    final
    EssentialSalaryRepository essentialSalaryRepository;


    public SalaryServiceImpl(SickLeaveService sickLeaveService, EmployeeService employeeService, OvertimeService overtimeService, SalaryRepository salaryRepository, OvertimeSalaryRepository overtimeSalaryRepository, EssentialSalaryRepository essentialSalaryRepository) {
        this.sickLeaveService = sickLeaveService;
        this.employeeService = employeeService;
        this.overtimeService = overtimeService;
        this.salaryRepository = salaryRepository;
        this.overtimeSalaryRepository = overtimeSalaryRepository;
        this.essentialSalaryRepository = essentialSalaryRepository;
    }

    @Override
    public Salary calculateSalary(int employeeId, int month, int year) {

        int sickLeaveMonth = month == 1 ? 12 : month - 1;
        int sickLeaveYear = month == 1 ? year - 1 : year;

        Salary salary = getEmployeeEssentialSalary(employeeId, month, year);

        //recalculate if already exists
        if (salary != null) {
            salary.setSickLeavesInMonth(sickLeaveService.getEmployeeSickLeavesMonthYear(employeeId, sickLeaveMonth, sickLeaveYear));
            salary.setSickLeavesUpToMonth(sickLeaveService.getEmployeeSickLeavesUpToMonthInYear(employeeId, sickLeaveMonth, sickLeaveYear));
            salary.setSalariesFromLast12Months(getEmployeeSalariesFromPrevious12Months(employeeId, month, year));
            salary.performCalculations();
        }
        //else create new
        else {
            salary = new EssentialSalary(employeeService.getEmployee(employeeId), month, year,
                    sickLeaveService.getEmployeeSickLeavesMonthYear(employeeId, sickLeaveMonth, sickLeaveYear),
                    sickLeaveService.getEmployeeSickLeavesUpToMonthInYear(employeeId, sickLeaveMonth, sickLeaveYear),
                    getEmployeeSalariesFromPrevious12Months(employeeId, month, year));

        }
        return salary;
    }


    @Override
    public List<Salary> calculateSalariesForActiveEmployees(int month, int year) {
        List<Employee> employees = employeeService.getActiveEmployees();
        List<Salary> salaries = new ArrayList<Salary>();
        LocalDate firstDayOfMonth = YearMonth.of(year, month).atDay(1);
        for (Employee employee:employees) {
            if (!employee.getEmployeedOnDate().isAfter(firstDayOfMonth)) {
                Salary salary = calculateSalary(employee.getId(), month, year);
                salaries.add(salary);
            }
        }
        return salaries;
    }

    @Override
    public Salary calculateOvertimeSalary(int employeeId, int month, int year) {

        Salary salary = getEmployeeOvertimeSalary(employeeId, month, year);

        if (salary != null) {
            salary.performCalculations();
        } else {
            Overtime ot = overtimeService.getEmployeeOvertimeInMonthYear(employeeId, month, year);
            if (ot != null)
                salary = new OvertimeSalary(employeeService.getEmployee(employeeId), month, year);
        }
        return salary;
    }

    @Override
    public List<Salary> calculateOvertimeSalaryForActiveEmployees(int month, int year) {
        List<Employee> employees = employeeService.getActiveEmployees();
        List<Salary> salaries = new ArrayList<Salary>();
        for (Employee employee:employees) {
            Salary salary = calculateOvertimeSalary(employee.getId(), month, year);
            if (salary != null)
                salaries.add(salary);
        }
        return salaries;
    }

    @Override
    public List<Salary> getSalariesForActiveEmployeesInMonthYear(int month, int year) {
        return salaryRepository.findAllByMonthAndYearAndEmployee_ActiveTrue(month, year);
    }

    @Override
    public List<Salary> getSalariesInMonthYear(int month, int year) {
        return salaryRepository.findAllByMonthAndYearOrderByEmployee_FirstName(month, year);
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
    public EssentialSalary getEmployeeEssentialSalary(int employeeId, int month, int year) {
        return essentialSalaryRepository.findByEmployee_IdAndMonthAndYear(employeeId, month, year);
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
    public List<EssentialSalary> getEssentialSalariesInMonthYear(int month, int year) {
        return essentialSalaryRepository.findAllByMonthAndYearOrderByEmployee_LastName(month, year);
    }

    @Override
    public List<OvertimeSalary> getOvertimeSalariesInMonthYear(int month, int year) {
        return overtimeSalaryRepository.findAllByMonthAndYearOrderByEmployee_LastName(month, year);
    }

    @Override
    public List<Salary> getEmployeeSalariesFromPrevious12Months(int employeeId, int month, int year) {
        int prevYear = year - 1;
        return salaryRepository.findAllByEmployeeIdAndMonthIsLessThanAndYearOrMonthGreaterThanEqualAndYear(employeeId, month, year, month, prevYear);
    }

    @Override
    public List<Salary> getEmployeeSalaries(int employeeId) {
        return salaryRepository.findByEmployee_IdOrderByYearDescMonthDesc(employeeId);
    }

    @Override
    public List<Salary> getEmployeeSalariesInMonthYear(String email, int month, int year) {
        return salaryRepository.findByEmployee_EmailAndMonthAndYear(email, month, year);
    }

    @Override
    public List<SalaryListDTO> getSalaryListDTO() {
        return salaryRepository.getAllPayrollMonths();
    }

    @Override
    public List<SalaryListDTO> getOvertimeSalaryListDTO() {
        return salaryRepository.getAllOvertimePayrollMonths();
    }

    @Override
    public List<SalaryListDTO> getEssentialSalaryListDTO() {
        return salaryRepository.getAllEssentialPayrollMonths();
    }

    @Override
    public List<SalaryListDTO> getEmployeeSalaryListDTO(String email) {
        return salaryRepository.getEmployeePayrollMonths(email);
    }

}
