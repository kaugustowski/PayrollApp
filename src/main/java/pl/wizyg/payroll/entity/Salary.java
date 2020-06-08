package pl.wizyg.payroll.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.IntStream;

@Entity
@Table(name = "salary")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Component
public abstract class Salary {

    @Column(name = "gross_salary")
    int grossSalary;
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
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "salary-generator"
    )
    @SequenceGenerator(
            name = "salary-generator",
            sequenceName = "salary_sequence"
    )
    private int id;
    @Column(name = "month")
    private int month;
    @Column(name = "year")
    private int year;
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
    @Column(name = "labor_fund")
    private int laborFund;
    @Column(name = "base_salary")
    private int baseSalary;

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary){
        this.baseSalary=baseSalary;
    }


    @Transient
    private List<SickLeave> sickLeavesInMonth;

    @Transient
    private List<SickLeave> sickLeavesUpToMonth;

    @Transient
    private List<Salary> salariesFromLast12Months;

    public Salary() {
    }

    public Salary(Employee employee, int month, int year, List<SickLeave> sickLeavesInMonth, List<SickLeave> sickLeavesUpToMonth, List<Salary> salariesFromLast12Months) {
        this.employee = employee;
        this.month = month;
        this.year = year;
        this.baseSalary=employee.getBaseSalary();
        this.sickLeavesInMonth = sickLeavesInMonth;
        this.sickLeavesUpToMonth = sickLeavesUpToMonth;
        this.salariesFromLast12Months = salariesFromLast12Months;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSickLeavesUpToMonth(List<SickLeave> sickLeavesUpToMonth) {
        this.sickLeavesUpToMonth = sickLeavesUpToMonth;
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

    public int getLaborFund() {
        return laborFund;
    }

    public void setLaborFund(int laborFund) {
        this.laborFund = laborFund;
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
        if (employee.isAllowedForExtraTaxDeductibleExpenses()) {
            taxDeductibleExpenses = SalaryConstants.EXTRA_TAX_DEDUCTIBLE_COST;
        } else {
            taxDeductibleExpenses = SalaryConstants.TAX_DEDUCTIBLE_COST;
        }
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

    //TODO
    public int getNumberOfSickLeaveDaysInCurrentMonth(List<SickLeave> sickLeavesInMonth) {
        int sickLeaveDaysInMonthYear = 0;
        for (SickLeave sickLeave : sickLeavesInMonth) {
            sickLeaveDaysInMonthYear += sickLeave.getNumberOfSickLeaveDaysInMonthYear(month, year);
        }
        return sickLeaveDaysInMonthYear;
    }

    //TODO
    public int getNumberOfSickLeaveDaysUpToMonth(List<SickLeave> sickLeavesUpToMonth) {
        int sickLeaveDaysUpToMonthYear = 0;
        for (SickLeave sickLeave : sickLeavesUpToMonth) {
            for (int i = 1; i < month; i++) {
                sickLeaveDaysUpToMonthYear += sickLeave.getNumberOfSickLeaveDaysInMonthYear(i, year);
            }
        }
        return sickLeaveDaysUpToMonthYear;
    }

    public int getNumberOfSickLeaveDaysUpToMonth() {
        int sickLeaveDaysUpToMonthYear = 0;
        for (SickLeave sickLeave : sickLeavesUpToMonth) {
            for (int i = 1; i < month; i++) {
                sickLeaveDaysUpToMonthYear += sickLeave.getNumberOfSickLeaveDaysInMonthYear(i, year);
            }
        }
        return sickLeaveDaysUpToMonthYear;
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
        return deductionsFromSalary;
    }

    public int calculatePayerDeductions() {
        int payerDeductions;

        payerDeductions = calculateDisabilityContributionPayer() + calculatePensionContributionPayer() + calculateAccidentInsuranceContribution() + calculateLabourFund();
        return payerDeductions;
    }

    public int calculateTax() {
        int tax = (int) Math.round((calculateTaxBase() * SalaryConstants.TAX_PERCENT / 100));

        return tax;
    }

    public int calculateTaxBase() {
        int taxBase = 0;
        taxBase = grossSalary - pensionContributionEmployee - disabilityContributionEmployee - sicknessContribution - getTaxDeductibleExpenses();

        return taxBase;
    }

    public int calculateIncomeTaxAdvance() {
        incomeTaxAdvance =
                (int) Math.round(calculateTax() - calculateHealthCareContributionDeduction() - SalaryConstants.TAX_DEDUCTION);
        return incomeTaxAdvance;
    }

    //ubezpieczenie emerytalne pracownika
    public int calculatePensionContributionEmployee() {
        pensionContributionEmployee = (int) Math.round(grossSalary * SalaryConstants.PENSION_CONTRIBUTION_EMPLOYEE_PERCENT / 100);

        return pensionContributionEmployee;
    }

    //ubezpieczenie emerytalne platnika
    public int calculatePensionContributionPayer() {
        pensionContributionPayer = (int) Math.round(grossSalary * SalaryConstants.PENSION_CONTRIBUTION_PAYER_PERCENT / 100);

        return pensionContributionPayer;
    }

    // ubezpieczenie rentowe pracownika
    public int calculateDisabilityContributionEmployee() {
        disabilityContributionEmployee = (int) Math.round(grossSalary * SalaryConstants.DISABILITY_CONTRIBUTION_EMPLOYEE_PERCENT / 100);

        return disabilityContributionEmployee;
    }

    // ubezpieczenie rentowe platnika
    public int calculateDisabilityContributionPayer() {
        disabilityContributionEmployee = (int) Math.round(grossSalary * SalaryConstants.DISABILITY_CONTRIBUTION_EMPLOYEE_PERCENT / 100);

        return disabilityContributionEmployee;
    }

    //ubezpieczenie wypadkowe
    public int calculateAccidentInsuranceContribution() {
        accidentInsuranceContribution = (int) Math.round(grossSalary * SalaryConstants.ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT / 100);

        return accidentInsuranceContribution;
    }

    //ubezpieczenie chorobowe
    public int calculateSicknessContribution() {

        sicknessContribution = (int) Math.round(grossSalary * SalaryConstants.ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT / 100);

        return sicknessContribution;
    }

    //podstawa ubezpieczenia zdrowotnego
    public int getHealthcareContributionBase() {
        return grossSalary - calculatePensionContributionEmployee() - calculateDisabilityContributionEmployee() - calculateSicknessContribution() - sicknessAllowance;
    }

    //ubezpieczenie zdrowotne
    public int calculateHealthCareContribution() {
        healthcareContribution = (int) Math.round(getHealthcareContributionBase() * SalaryConstants.HEALTHCARE_CONTRIBUTION_PERCENT / 100);

        return healthcareContribution;
    }

    //
    public int calculateHealthCareContributionDeduction() {
        healthcareContributionDeduction = (int) Math.round(getHealthcareContributionBase() * SalaryConstants.HEALTHCARE_CONTRIBUTION_DEDUCTION_PERCENT / 100);

        return healthcareContributionDeduction;
    }

    public int calculateSickPay(int sickPayBase) {
        return (int) Math.round(sickPayBase * ((double) getNumberOfSickLeaveDaysInCurrentMonth() / 30));
    }

    //TODO
    public int calculateSickPayBase2() {
        return grossSalary;
    }


    public int calculateSickPayBase() {
        int sickPayBaseSum = 0;
        int i = 0;

        for (Salary salary : salariesFromLast12Months) {
            if (salary.isAllowedForSickPayBaseCalculation()) {
                sickPayBaseSum += salary.getHealthcareContributionBase();
                i++;
            }
        }
        return (int) Math.round((double) sickPayBaseSum / i);
    }

    protected abstract boolean isAllowedForSickPayBaseCalculation();

    public int getNumberOfWorkdays() {
        int numberOfWorkDays = 0;
        YearMonth yearMonth = YearMonth.of(year, month);

        // java 8
        LocalDate[] freeDays =
                (LocalDate[]) IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                        .mapToObj(day -> LocalDate.of(year, month, day))
                        .filter(this::isFreeDay).toArray();

        numberOfWorkDays = yearMonth.lengthOfMonth() - freeDays.length;

        //java 11+
//        LocalDate[] weekendDays =
//                (LocalDate[]) yearMonth.atDay(1).datesUntil(yearMonth.atEndOfMonth())
//                        .filter(date -> isWeekend(date)).toArray();
//
//        numberOfWorkDays=yearMonth.lengthOfMonth()-weekendDays.length;


        return numberOfWorkDays;
    }


    //TODO jkbjjjh
    public int getNumberOfWorkedDaysWithSickLeave() {
        int numberOfUnworkedDays = 0;
        YearMonth yearMonth = YearMonth.of(year, month);

        // java 8
        LocalDate[] skippedDays =
                (LocalDate[]) IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                        .mapToObj(day -> LocalDate.of(year, month, day))
                        .filter(date -> isFreeDay(date) || getSickLeavesInMonth().contains(date)).toArray();

        numberOfUnworkedDays = yearMonth.lengthOfMonth() - skippedDays.length;

        //java 11+
//        LocalDate[] weekendDays =
//                (LocalDate[]) yearMonth.atDay(1).datesUntil(yearMonth.atEndOfMonth())
//                        .filter(date -> isWeekend(date)).toArray();
//
//        numberOfWorkDays=yearMonth.lengthOfMonth()-weekendDays.length;

        return numberOfUnworkedDays;
    }

    public boolean
    isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY;

    }

    public boolean isFreeDay(LocalDate date) {
        return isWeekend(date) || Holidays.getHolidaysInYear(year).contains(date);
    }

//    public int getNumberOfSickLeaveDaysUpToMonth() {
//        int numberOfSickleaveDaysThisYear = 0;
//
//        for(int i=1; i <= month; i++){
//            numberOfSickleaveDaysThisYear += getNumberOfSickLeaveDaysInMonthYear();
//        }
//        return numberOfSickleaveDaysThisYear;
//    }

    public int calculateSicknessAllowance() {

        int sickLeaveDaysThisYear = getNumberOfSickLeaveDaysUpToMonth() + getNumberOfSickLeaveDaysInCurrentMonth();

        int daysOverLimit = sickLeaveDaysThisYear - getSickLeaveLimit();

        if (daysOverLimit > 0) {
            sicknessAllowance = (int) Math.round(sickPay * ((double) daysOverLimit / getNumberOfSickLeaveDaysInCurrentMonth()));
        } else {
            sicknessAllowance = 0;
        }

        sickPay -= sicknessAllowance;

        return sicknessAllowance;
    }


    public void performCalculations() {
        calculateGrossSalary();
        calculateEmployeeDeductionsFromSalary();
        calculateSicknessContribution();
        calculatePayerDeductions();
        calculateSickPay(calculateSickPayBase());
        calculateSicknessAllowance();
        calculateHealthCareContribution();
        calculateIncomeTaxAdvance();
    }

    public int getNetSalary(){
        return grossSalary+ sickPay+sicknessAllowance-calculateEmployeeDeductionsFromSalary();
    }

    private int calculateLabourFund() {
        laborFund = (int) Math.round(grossSalary * SalaryConstants.LABOUR_FUND_PERCENT / 100);
        return laborFund;
    }

    public int getSickLeaveLimit() {
        int limit;
        if (employee.isOver55inYear(year)) {
            limit = 14;
        } else {
            limit = 33;
        }
        return limit;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "grossSalary=" + grossSalary +
                ", month=" + month +
                ", year=" + year +
                ", pensionContributionPayer=" + pensionContributionPayer +
                ", disabilityContributionPayer=" + disabilityContributionPayer +
                ", accidentInsuranceContribution=" + accidentInsuranceContribution +
                ", pensionContributionEmployee=" + pensionContributionEmployee +
                ", disabilityContributionEmployee=" + disabilityContributionEmployee +
                ", sicknessContribution=" + sicknessContribution +
                ", healthcareContribution=" + healthcareContribution +
                ", healthcareContributionDeduction=" + healthcareContributionDeduction +
                ", employee=" + employee +
                ", id=" + id +
                ", tax=" + tax +
                ", incomeTaxAdvance=" + incomeTaxAdvance +
                ", taxDeductibleExpenses=" + taxDeductibleExpenses +
                ", sickPay=" + sickPay +
                ", sicknessAllowance=" + sicknessAllowance +
                ", laborFund=" + laborFund +
                '}';
    }
}

