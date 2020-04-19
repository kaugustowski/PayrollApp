package pl.wizyg.payroll.entity;


import javax.persistence.*;

@Entity
@Table(name = "salary")
@Inheritance(strategy = InheritanceType.JOINED)
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "gross_salary")
    private double grossSalary;

    @Column(name = "pension_contribution_payer")
    private double pensionContributionPayer;

    @Column(name = "disability_contribution_payer")
    private double disabilityContributionPayer;

    @Column(name = "accident_insurance_contribution_payer")
    private double accidentInsuranceContributionPayer;

    @Column(name = "pension_contribution_employee")
    private double pensionContributionEmployee;

    @Column(name = "disability_contribution_employee")
    private double disabilityContributionEmployee;

    @Column(name = "sickness_contribution_employee")
    private double sicknessContributionEmployee;

    @Column(name = "tax")
    private double tax;

    @Column(name = "income_tax_advance")
    private double incomeTaxAdvance;

    @Column(name = "tax_deductible_expenses")
    private double taxDeductibleExpenses;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Salary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getPensionContributionPayer() {
        return pensionContributionPayer;
    }

    public void setPensionContributionPayer(double pensionContributionPayer) {
        this.pensionContributionPayer = pensionContributionPayer;
    }

    public double getDisabilityContributionPayer() {
        return disabilityContributionPayer;
    }

    public void setDisabilityContributionPayer(double disabilityContributionPayer) {
        this.disabilityContributionPayer = disabilityContributionPayer;
    }

    public double getAccidentInsuranceContributionPayer() {
        return accidentInsuranceContributionPayer;
    }

    public void setAccidentInsuranceContributionPayer(double accidentInsuranceContributionPayer) {
        this.accidentInsuranceContributionPayer = accidentInsuranceContributionPayer;
    }

    public double getPensionContributionEmployee() {
        return pensionContributionEmployee;
    }

    public void setPensionContributionEmployee(double pensionContributionEmployee) {
        this.pensionContributionEmployee = pensionContributionEmployee;
    }

    public double getDisabilityContributionEmployee() {
        return disabilityContributionEmployee;
    }

    public void setDisabilityContributionEmployee(double disabilityContributionEmployee) {
        this.disabilityContributionEmployee = disabilityContributionEmployee;
    }

    public double getSicknessContributionEmployee() {
        return sicknessContributionEmployee;
    }

    public void setSicknessContributionEmployee(double sicknessContributionEmployee) {
        this.sicknessContributionEmployee = sicknessContributionEmployee;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getIncomeTaxAdvance() {
        return incomeTaxAdvance;
    }

    public void setIncomeTaxAdvance(double incomeTaxAdvance) {
        this.incomeTaxAdvance = incomeTaxAdvance;
    }

    public double getTaxDeductibleExpenses() {
        return taxDeductibleExpenses;
    }

    public void setTaxDeductibleExpenses(double taxDeductibleExpenses) {
        this.taxDeductibleExpenses = taxDeductibleExpenses;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public double calculateGrossSalary() {
        grossSalary = teacher.getBaseSalary() + teacher.getIncentivePay() + teacher.getSeniorityBonus();
        return grossSalary;
    }


    public double calculateDeductionsFromSalary() {
        double deductionsFromSalary;

        deductionsFromSalary = calculateTax() + calculateSicknessContribution() + calculatePensionContribution()
                + calculateDisabilityContribution() + calculateAccidentInsuranceContribution();
        return 0;
    }

    public double calculatePayerDeductions() {
        return 0;
    }

    public double calculateTax() {
        return 0;
    }

    //ubezpieczenie emerytalne
    public double calculatePensionContribution() {
        double pensionContribution;
        final double PENSION_CONTRIBUTION_PERCENT = 9.76;

        pensionContribution = grossSalary * PENSION_CONTRIBUTION_PERCENT / 100;

        return pensionContribution;
    }

    // ubezpieczenie rentowe
    public double calculateDisabilityContribution() {
        double disabilityContribution;
        final double DISABILITY_CONTRIBUTION_PERCENT = 9.76;

        disabilityContribution = grossSalary * DISABILITY_CONTRIBUTION_PERCENT / 100;

        return disabilityContribution;
    }

    //ubezpieczenie wypadkowe
    public double calculateAccidentInsuranceContribution() {
        double accidentInsuranceContribution;
        final double ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT = 0.93;
        accidentInsuranceContribution = grossSalary * ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT / 100;

        return accidentInsuranceContribution;
    }

    //ubezpieczenie chorobowe
    public double calculateSicknessContribution() {
        double sicknessContribution;
        final double SICKNESS_CONTRIBUTION_PERCENT = 2.45;
        sicknessContribution = grossSalary * SICKNESS_CONTRIBUTION_PERCENT / 100;

        return sicknessContribution;
    }

    //ubezpieczenie zdrowotne
    public double calculateHealthCareContribution() {
        double healthcareContribution;
        final double HEALTHCAR_CONTRIBUTION_PERCENT = 9;
        healthcareContribution = grossSalary * HEALTHCAR_CONTRIBUTION_PERCENT / 100;

        return healthcareContribution;
    }

    //
    public double calculateHealthCareContributionDecuction() {
        double healthcareContribution;
        final double HEALTHCARE_CONTRIBUTION_DEDUCTION_PERCENT = 7.75;

        healthcareContribution = grossSalary * HEALTHCARE_CONTRIBUTION_DEDUCTION_PERCENT;

        return healthcareContribution;

    }


}
