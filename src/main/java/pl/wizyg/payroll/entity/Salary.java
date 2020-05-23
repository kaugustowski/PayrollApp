package pl.wizyg.payroll.entity;


import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Table(name = "salary")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Salary {

    @Column(name = "gross_salary")
    int grossSalary;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;
    @Column(name = "pension_contribution_payer")
    int pensionContributionPayer;
    @Column(name = "disability_contribution_payer")
    int disabilityContributionPayer;
    @Column(name = "accident_insurance_contribution")
    int accidentInsuranceContribution;
    @Column(name = "pension_contribution_employee")
    int pensionContributionEmployee;
    @Column(name = "disability_contribution_employee")
    int disabilityContributionEmployee;
    @Column(name = "sickness_contribution")
    int sicknessContribution;
    @Column(name = "healthcare_contribution")
    int healthcareContribution;
    @Column(name = "healthcare_contribution_deduction")
    int healthcareContributionDeduction;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "tax")
    private int tax;
    @Column(name = "income_tax_advance")
    private int incomeTaxAdvance;
    @Column(name = "tax_deductible_expenses")
    private int taxDeductibleExpenses;
    @Column(name = "sick_pay")
    private int sickPay;
    @Column(name = "sickness_allowance")
    private int sicknessAllowance;
    @Transient
    private List<SickLeave> sickLeavesInMonth;


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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getHealthcareContribution() {
        return healthcareContribution;
    }

    public void setHealthcareContribution(int healthcareContribution) {
        this.healthcareContribution = healthcareContribution;
    }

    public int getHealthcareContributionDeduction() {
        return healthcareContributionDeduction;
    }

    public void setHealthcareContributionDeduction(int healthcareContributionDeduction) {
        this.healthcareContributionDeduction = healthcareContributionDeduction;
    }

    public int getSickPay() {
        return sickPay;
    }

    public void setSickPay(int sickPay) {
        this.sickPay = sickPay;
    }

    public int getSicknessAllowance() {
        return sicknessAllowance;
    }

    public void setSicknessAllowance(int sicknessAllowance) {
        this.sicknessAllowance = sicknessAllowance;
    }

    public List<SickLeave> getSickLeavesInMonth() {
        return sickLeavesInMonth;
    }

    public void setSickLeavesInMonth(List<SickLeave> sickLeavesInMonth) {
        this.sickLeavesInMonth = sickLeavesInMonth;
    }

    public int getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(int grossSalary) {
        this.grossSalary = grossSalary;
    }

    public int getPensionContributionPayer() {
        return pensionContributionPayer;
    }

    public void setPensionContributionPayer(int pensionContributionPayer) {
        this.pensionContributionPayer = pensionContributionPayer;
    }

    public int getDisabilityContributionPayer() {
        return disabilityContributionPayer;
    }

    public void setDisabilityContributionPayer(int disabilityContributionPayer) {
        this.disabilityContributionPayer = disabilityContributionPayer;
    }

    public int getAccidentInsuranceContribution() {
        return accidentInsuranceContribution;
    }

    public void setAccidentInsuranceContribution(int accidentInsuranceContributionPayer) {
        this.accidentInsuranceContribution = accidentInsuranceContributionPayer;
    }

    public int getPensionContributionEmployee() {
        return pensionContributionEmployee;
    }

    public void setPensionContributionEmployee(int pensionContributionEmployee) {
        this.pensionContributionEmployee = pensionContributionEmployee;
    }

    public int getDisabilityContributionEmployee() {
        return disabilityContributionEmployee;
    }

    public void setDisabilityContributionEmployee(int disabilityContributionEmployee) {
        this.disabilityContributionEmployee = disabilityContributionEmployee;
    }

    public int getSicknessContribution() {
        return sicknessContribution;
    }

    public void setSicknessContribution(int sicknessContributionEmployee) {
        this.sicknessContribution = sicknessContributionEmployee;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getIncomeTaxAdvance() {
        return incomeTaxAdvance;
    }

    public void setIncomeTaxAdvance(int incomeTaxAdvance) {
        this.incomeTaxAdvance = incomeTaxAdvance;
    }

    public int getTaxDeductibleExpenses() {
        return taxDeductibleExpenses;
    }

    public void setTaxDeductibleExpenses(int taxDeductibleExpenses) {
        this.taxDeductibleExpenses = taxDeductibleExpenses;
    }

    public int getIncentivePay() {
        return 0;
    }

    public int getNumberOfSickLeaveDaysInCurrentMonth() {
        int sickLeaveDaysInMonthYear = 0;
        for (SickLeave sickLeave : sickLeavesInMonth) {
            sickLeaveDaysInMonthYear += sickLeave.getNumberOfSickLeaveDaysInMonthYear(month, year);
        }
        return sickLeaveDaysInMonthYear;
    }


    public int calculateGrossSalary() {
        grossSalary = employee.getBaseSalary() + employee.getSeniorityBonus() + employee.getIncentivePay() + employee.getFunctionalBonus();

        grossSalary *= (double) (30 - getNumberOfSickLeaveDaysInCurrentMonth()) / 30;

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

    //ubezpieczenie emerytalne pracownika
    public int calculatePensionContributionEmployee() {
        pensionContributionEmployee = (int) (grossSalary * SalaryConstants.PENSION_CONTRIBUTION_EMPLOYEE_PERCENT / 100);

        return pensionContributionEmployee;
    }

    //ubezpieczenie emerytalne platnika
    public int calculatePensionContributionPayer() {
        pensionContributionPayer = (int) (grossSalary * SalaryConstants.PENSION_CONTRIBUTION_PAYER_PERCENT / 100);

        return pensionContributionPayer;
    }

    // ubezpieczenie rentowe pracownika
    public int calculateDisabilityContributionEmployee() {
        disabilityContributionEmployee = (int) (grossSalary * SalaryConstants.DISABILITY_CONTRIBUTION_EMPLOYEE_PERCENT / 100);

        return disabilityContributionEmployee;
    }

    // ubezpieczenie rentowe platnika
    public int calculateDisabilityContributionPayer() {
        disabilityContributionEmployee = (int) (grossSalary * SalaryConstants.DISABILITY_CONTRIBUTION_EMPLOYEE_PERCENT / 100);

        return disabilityContributionEmployee;
    }

    //ubezpieczenie wypadkowe
    public int calculateAccidentInsuranceContribution() {
        accidentInsuranceContribution = (int) (grossSalary * SalaryConstants.ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT / 100);

        return accidentInsuranceContribution;
    }

    //ubezpieczenie chorobowe
    public int calculateSicknessContribution() {

        sicknessContribution = (int) (grossSalary * SalaryConstants.ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT / 100);

        return sicknessContribution;
    }

    //podstawa ubezpieczenia zdrowotnego
    public int getHealthcareContributionBase() {
        return grossSalary - calculateEmployeeDeductionsFromSalary() - sicknessAllowance;
    }

    //ubezpieczenie zdrowotne
    public int calculateHealthCareContribution() {
        healthcareContribution = (int) (getHealthcareContributionBase() * SalaryConstants.HEALTHCARE_CONTRIBUTION_PERCENT / 100);

        return healthcareContribution;
    }

    //
    public int calculateHealthCareContributionDeduction() {
        healthcareContributionDeduction = (int) (getHealthcareContributionBase() * SalaryConstants.HEALTHCARE_CONTRIBUTION_DEDUCTION_PERCENT / 100);

        return healthcareContributionDeduction;
    }

    public int getNumberOfWorkdays() {
        int numberOfWorkDays = 0;
        YearMonth yearMonth = YearMonth.of(year, month);

        // java 8
        LocalDate[] freeDays =
                (LocalDate[]) IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                        .mapToObj(day -> LocalDate.of(year, month, day))
                        .filter(date -> isWeekend(date) || Holidays.getHolidaysInYear(year).contains(date)).toArray();

        numberOfWorkDays = yearMonth.lengthOfMonth() - freeDays.length;

        //java 11+
//        LocalDate[] weekendDays =
//                (LocalDate[]) yearMonth.atDay(1).datesUntil(yearMonth.atEndOfMonth())
//                        .filter(date -> isWeekend(date)).toArray();
//
//        numberOfWorkDays=yearMonth.lengthOfMonth()-weekendDays.length;


        return numberOfWorkDays;
    }

    public boolean
    isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY;

    }

    public boolean isFreeDay(LocalDate date) {
        return isWeekend(date) || Holidays.getHolidaysInYear(year).contains(date);
    }

    public int getNumberOfSickleaveDaysUpToMonth() {
        int numberOfSickleaveDaysThisYear = 0;

        return numberOfSickleaveDaysThisYear;
    }


}
