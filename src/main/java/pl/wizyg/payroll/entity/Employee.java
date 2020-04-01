package pl.wizyg.payroll.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Component
@MappedSuperclass
public abstract class Employee {

    @Column(name = "base_salary")
    protected double baseSalary;
    @Column(name = "seniority_bonus")
    protected double seniorityBonus;
    @Column(name = "functional_bonus")
    protected double functionalBonus;
    @Column(name = "salary")
    protected double salary;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "pesel")
    private String pesel;
    @Column(name = "email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getSeniorityBonus() {
        return seniorityBonus;
    }

    public void setSeniorityBonus(double seniorityBonus) {
        this.seniorityBonus = seniorityBonus;
    }

    public double getFunctionalBonus() {
        return functionalBonus;
    }

    public void setFunctionalBonus(double functionalBonus) {
        this.functionalBonus = functionalBonus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double calculateDeductionsFromSalary() {
        double deductionsFromSalary;

        deductionsFromSalary = calculateTax() + calculateSicknessContribution() + calculatePensionContribution()
                + calculateDisabilityContribution() + calculateAccidentInsuranceContribution();
        return 0;
    }

    public double calculateEmployerDeductions() {
        return 0;
    }

    public double calculateTax() {
        return 0;
    }

    //ubezpieczenie emerytalne
    public double calculatePensionContribution() {
        double pensionContribution;
        final double PENSION_CONTRIBUTION_PERCENT = 9.76;

        pensionContribution = salary * PENSION_CONTRIBUTION_PERCENT / 100;

        return pensionContribution;
    }

    // ubezpieczenie rentowe
    public double calculateDisabilityContribution() {
        double disabilityContribution;
        final double PENSION_CONTRIBUTION_PERCENT = 9.76;

        disabilityContribution = salary * PENSION_CONTRIBUTION_PERCENT / 100;

        return disabilityContribution;
    }

    //ubezpieczenie wypadkowe
    public double calculateAccidentInsuranceContribution() {
        double accidentInsuranceContribution;
        final double ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT = 0.93;
        accidentInsuranceContribution = salary * ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT / 100;

        return accidentInsuranceContribution;
    }

    //ubezpieczenie chorobowe
    public double calculateSicknessContribution() {
        double sicknessContribution;
        final double SICKNESS_CONTRIBUTION_PERCENT = 2.45;
        sicknessContribution = salary * SICKNESS_CONTRIBUTION_PERCENT / 100;

        return sicknessContribution;
    }

    //ubezpieczenie zdrowotne
    public double calculateHealthCareContribution() {
        double healthcareContribution;
        final double HEALTHCAR_CONTRIBUTION_PERCENT = 9;
        healthcareContribution = salary * HEALTHCAR_CONTRIBUTION_PERCENT / 100;

        return healthcareContribution;
    }

    //
    public double calculateHealthCareContributionDecuction() {
        double healthcareContribution;
        final double HEALTHCAR_CONTRIBUTION_PERCENT = 7.75;

        healthcareContribution = salary * HEALTHCAR_CONTRIBUTION_PERCENT;

        return healthcareContribution;

    }
}
