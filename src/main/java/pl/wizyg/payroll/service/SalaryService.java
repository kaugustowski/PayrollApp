package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.OvertimeSalary;
import pl.wizyg.payroll.entity.Salary;

import java.util.List;

public interface SalaryService {

    public Salary calculateSalary(int employeeId, int month, int year);

    public List<Salary> calculateSalariesForActiveEmployees(int month, int year);

    Salary calculateOvertimeSalary(int employeeId, int month, int year);

    public List<Salary> calculateOvertimeSalaryForActiveEmployees(int month, int year);

    public void saveSalary(Salary salary);

    public void saveSalaries(List<Salary> salaries);

    public Salary getEssentialSalary(int employeeId, int month, int year);

    Salary getOvertimeSalary(int id);

    public List<Salary> getEssentialSalariesInMonthYear(int month, int year);

    public List<OvertimeSalary> getOvertimeSalariesInMonthYear(int month, int year);

    List<Salary> getSalariesFromLast12Months(int month, int year);
}
