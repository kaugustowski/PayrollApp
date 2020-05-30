package pl.wizyg.payroll.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "essential_salary")
public class EssentialSalary extends Salary {

    public EssentialSalary(){}

    public EssentialSalary(Employee employee, int month, int year, List<SickLeave> employeesSickLeavesMonthYear, List<SickLeave> employeesSickLeavesUpToMonthInYear, List<Salary> salariesFromLast12Months) {
        super(employee, month, year, employeesSickLeavesMonthYear,employeesSickLeavesUpToMonthInYear, salariesFromLast12Months);
    }

    @Override
    public boolean isAllowedForSickPayBaseCalculation() {
          return workedDaysRatio() >= 0.5;
    }

    public double workedDaysRatio(){
        return (double)getNumberOfWorkedDaysWithSickLeave()/getNumberOfWorkdays();
    }
}
