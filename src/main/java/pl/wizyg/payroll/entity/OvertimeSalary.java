package pl.wizyg.payroll.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "overtime_salary")
public class OvertimeSalary extends Salary {

    @Column(name = "number_of_overtime_hours")
    private int numberOfOvertimeHours;

    public OvertimeSalary() {}

    public OvertimeSalary(Employee employee, int month, int year) {
    }

    public int getNumberOfOvertimeHours() {
        return numberOfOvertimeHours;
    }

    public void setNumberOfOvertimeHours(int numberOfOvertimeHours) {
        this.numberOfOvertimeHours = numberOfOvertimeHours;
    }


    public int calculateContributionBaseOvertime(Overtime overtime) {
        int overtimeHours = overtime.getNumberOfOverTimeHoursInCurrentMonth();
        contributionBase = employee.getOvertimeHourRate()*overtimeHours;

        return contributionBase;
    }

    public int calculateGrossSalaryOvertime(Overtime overtime) {
        int overtimeHours = overtime.getNumberOfOverTimeHoursInCurrentMonth();
        grossSalary = employee.getOvertimeHourRate()*overtimeHours;

        return contributionBase;
    }



    @Override
    public int calculateTax() {
        return (int) Math.round((roundToHundreds(calculateTaxBase()) * SalaryConstants.TAX_PERCENT / 100));
    }

    @Override
    public int calculateTaxBase() {
        int taxBase = 0;
        taxBase = grossSalary - pensionContributionEmployee - disabilityContributionEmployee - sicknessContribution;

        return taxBase;
    }

    @Override
    public int calculateIncomeTaxAdvance() {
        incomeTaxAdvance =
                roundToHundreds(Math.round(calculateTax() - calculateHealthCareContributionDeduction()));
        return incomeTaxAdvance;
    }

    @Override
    protected boolean isAllowedForSickPayBaseCalculation() {
        return true;
    }

    @Override
    public void performCalculations() {
        calculateContributionBase();
        calculateEmployeeContribution();
        calculatePayerDeductions();
        calculateSickPay(calculateSickPayBase());
        calculateSicknessAllowance();
        calculateGrossSalary();
        calculateHealthCareContribution();
        calculateIncomeTaxAdvance();
        calculateEmployeeDeductionsFromSalary();
        getNetSalary();
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
