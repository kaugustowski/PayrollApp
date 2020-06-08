package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.OvertimeSalary;
import pl.wizyg.payroll.entity.Salary;

import java.util.List;

public interface SalaryService {

    Salary calculateSalary(int employeeId, int month, int year);

    List<Salary> calculateSalariesForActiveEmployees(int month, int year);

    Salary calculateOvertimeSalary(int employeeId, int month, int year);

    List<Salary> calculateOvertimeSalaryForActiveEmployees(int month, int year);

    List<Salary> getSalariesForActiveEmployeesInMonthYear(int month, int year);

    void saveSalary(Salary salary, int employeeId);

    void saveSalaries(List<Salary> salaries);

    Salary getEmployeeEssentialSalary(int employeeId, int month, int year);

    Salary getEssentialSalary(int id);

    OvertimeSalary getOvertimeSalary(int id);

    abstract Salary getEmployeeOvertimeSalary(int employeeId, int month, int year);

    List<Salary> getEssentialSalariesInMonthYear(int month, int year);

    List<OvertimeSalary> getOvertimeSalariesInMonthYear(int month, int year);

    List<Salary> getEmployeeSalariesFromPrevious12Months(int employeeId, int month, int year);
}
