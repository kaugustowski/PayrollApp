package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Salary;

import java.util.List;

public class SalaryServiceImpl implements SalaryService {

    @Override
    public void calculateSalary(int employeeId, int month, int year) {

    }

    @Override
    public void calculateSalariesForActiveEmployees() {

    }

    @Override
    public void calculateOvertimeSalaryForActiveEmployees() {

    }

    @Override
    public void saveSalary(Salary salary) {

    }

    @Override
    public void saveSalaries(List<Salary> salaries) {

    }


    @Override
    public Salary getEssentialSalary() {
        return null;
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
