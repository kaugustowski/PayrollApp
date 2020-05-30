package pl.wizyg.payroll.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "overtime_salary")
public class OvertimeSalary extends Salary {

    @Column(name = "number_of_overtime_hours")
    private int numberOfOvertimeHours;

    public OvertimeSalary(Employee employee, int month, int year) {
    }

    public int getNumberOfOvertimeHours() {
        return numberOfOvertimeHours;
    }

    public void setNumberOfOvertimeHours(int numberOfOvertimeHours) {
        this.numberOfOvertimeHours = numberOfOvertimeHours;
    }


    public int calculateGrossSalaryOvertime(Overtime overtime) {
        int overtimeHours = overtime.getNumberOfOverTimeHoursInCurrentMonth();
        grossSalary = employee.getOvertimeHourRate()*overtimeHours;

        return grossSalary;
    }


    public int calculateEmployeeDeductionsFromSalary() {
        int deductionsFromSalary;

        deductionsFromSalary = calculateIncomeTaxAdvance() + calculateSicknessContribution() + calculatePensionContributionEmployee()
                + calculateDisabilityContributionEmployee();
        return 0;
    }

    public int calculatePayerDeductions() {
        return 0;
    }

    public int calculateTax() {
        return 0;
    }

    public int calculateIncomeTaxAdvance() {

        return 0;
    }


    //podstawa ubezpieczenia zdrowotnego
    public int getHealthcareContributionBase() {
        return grossSalary;
    }

    //ubezpieczenie zdrowotne
    public int calculateHealthCareContribution() {
        healthcareContribution = (int) (getHealthcareContributionBase() * SalaryConstants.HEALTHCARE_CONTRIBUTION_PERCENT / 100);

        return healthcareContribution;
    }

    //ubezpieczenie zdrowotne odejmowane od podatku
    public int calculateHealthCareContributionDeduction() {
        healthcareContributionDeduction = (int) (getHealthcareContributionBase() * SalaryConstants.HEALTHCARE_CONTRIBUTION_DEDUCTION_PERCENT / 100);

        return healthcareContributionDeduction;
    }

    @Override
    protected boolean isAllowedForSickPayBaseCalculation() {
        return true;
    }
}
