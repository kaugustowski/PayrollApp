package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Salary;

import java.util.List;

public interface SalaryService {

    public void calculateSalary(int employeeId, int month, int year);

    public void calculateSalariesForActiveEmployees(int month, int year);

    public void calculateOvertimeSalaryForActiveEmployees(int month, int year);

    public void saveSalary(Salary salary);

    public void saveSalaries(List<Salary> salaries);

    public Salary getEssentialSalary(int employeeId, int month, int year);

    public Salary getOvertimeSalary();

    public List<Salary> getEssentialSalariesInMonthYear(int month, int year);

    public List<Salary> getOvertimeSalariesInMonthYear(int month, int year);


}
