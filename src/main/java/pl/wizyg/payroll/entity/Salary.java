package pl.wizyg.payroll.entity;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    private double grossSalary;


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


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

        pensionContribution = grossSalary * PENSION_CONTRIBUTION_PERCENT / 100;

        return pensionContribution;
    }

    // ubezpieczenie rentowe
    public double calculateDisabilityContribution() {
        double disabilityContribution;
        final double PENSION_CONTRIBUTION_PERCENT = 9.76;

        disabilityContribution = grossSalary * PENSION_CONTRIBUTION_PERCENT / 100;

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
        final double HEALTHCAR_CONTRIBUTION_PERCENT = 7.75;

        healthcareContribution = grossSalary * HEALTHCAR_CONTRIBUTION_PERCENT;

        return healthcareContribution;

    }


}
