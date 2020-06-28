package pl.wizyg.payroll.entity;

import pl.wizyg.payroll.helper.SalaryConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "overtime_salary")
public class OvertimeSalary extends Salary {

    @Column(name = "number_of_overtime_hours")
    @Min(0)
    private int numberOfOvertimeHours;

    public OvertimeSalary() {}

    public OvertimeSalary(Employee employee, int month, int year) {
        this.employee = employee;

        this.numberOfOvertimeHours = employee.getOvertimeList()
                .stream()
                .filter(overtime -> overtime.getMonth() == month && overtime.getYear() == year)
                .findFirst()
                .map(Overtime::getNumberOfOverTimeHoursInCurrentMonth)
                .orElse(0);

        this.month = month;
        this.year = year;

        performCalculations();
    }

    public int getNumberOfOvertimeHours() {
        return numberOfOvertimeHours;
    }

    public void setNumberOfOvertimeHours(int numberOfOvertimeHours) {
        this.numberOfOvertimeHours = numberOfOvertimeHours;
    }


    public int calculateContributionBaseOvertime() {
        contributionBase = employee.getOvertimeHourRate() * numberOfOvertimeHours;
        return contributionBase;
    }

    public int calculateGrossSalaryOvertime() {
        grossSalary = employee.getOvertimeHourRate() * numberOfOvertimeHours;
        return grossSalary;
    }

    @Override
    public int calculateTaxBase() {
        return grossSalary
                - pensionContributionEmployee
                - disabilityContributionEmployee
                - sicknessContribution;
    }

    @Override
    public int calculateIncomeTaxAdvance() {
        incomeTaxAdvance =
                roundToHundreds(Math.round(calculateTax() - calculateHealthCareContributionDeduction()));
        return incomeTaxAdvance;
    }

    @Override
    public int calculateTax() {
        tax = (int) Math.round((roundToHundreds(calculateTaxBase()) * SalaryConstants.TAX_PERCENT / 100));
        return tax;
    }

    @Override
    protected boolean isAllowedForSickPayBaseCalculation() {
        return true;
    }

    @Override
    public void performCalculations() {
        calculateGrossSalaryOvertime();
        calculateContributionBaseOvertime();
        calculateEmployeeContribution();
        calculatePayerDeductions();
        calculateHealthCareContribution();
        calculateIncomeTaxAdvance();
        calculateEmployeeDeductionsFromSalary();
        calculateNetSalary();
    }

    @Override
    public String toString() {
        return "OvertimeSalary{" +
                "numberOfOvertimeHours=" + numberOfOvertimeHours +
                ", grossSalary=" + grossSalary +
                ", contributionBase=" + contributionBase +
                ", pensionContributionPayer=" + pensionContributionPayer +
                ", disabilityContributionPayer=" + disabilityContributionPayer +
                ", accidentInsuranceContribution=" + accidentInsuranceContribution +
                ", pensionContributionEmployee=" + pensionContributionEmployee +
                ", disabilityContributionEmployee=" + disabilityContributionEmployee +
                ", sicknessContribution=" + sicknessContribution +
                ", healthcareContribution=" + healthcareContribution +
                ", healthcareContributionDeduction=" + healthcareContributionDeduction +
                ", employee=" + employee +
                ", incomeTaxAdvance=" + incomeTaxAdvance +
                '}';
    }

}
