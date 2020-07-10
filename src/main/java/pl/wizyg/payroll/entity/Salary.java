package pl.wizyg.payroll.entity;


import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.wizyg.payroll.helper.MyDateUtils;
import pl.wizyg.payroll.helper.SalaryConstants;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
@Table(name = "salary")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Component
public abstract class Salary {

    @Column(name = "income_tax_advance")
    @Min(0)
    protected int incomeTaxAdvance;
    @Transient
    int contributionBase;
    @Min(0)
    @Column(name = "gross_salary")
    int grossSalary;
    @Column(name = "pension_contribution_payer")
    @Min(0)
    int pensionContributionPayer;
    @Column(name = "disability_contribution_payer")
    @Min(0)
    int disabilityContributionPayer;
    @Column(name = "accident_insurance_contribution")
    @Min(0)
    int accidentInsuranceContribution;
    @Column(name = "pension_contribution_employee")
    @Min(0)
    int pensionContributionEmployee;
    @Column(name = "disability_contribution_employee")
    @Min(0)
    int disabilityContributionEmployee;
    @Column(name = "sickness_contribution")
    @Min(0)
    int sicknessContribution;
    @Column(name = "healthcare_contribution")
    @Min(0)
    int healthcareContribution;
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
    @Column(name = "healthcare_contribution_deduction")
    @Min(0)
    int healthcareContributionDeduction;
    @Column(name = "month")
    @Min(1)
    @Max(12)
    int month;
    @Column(name = "year")
    @Min(0)
    int year;
    @Column(name = "tax")
    @Min(0)
    int tax;
    @Column(name = "tax_deductible_expenses")
    @Min(0)
    private int taxDeductibleExpenses;
    @Column(name = "sick_pay")
    @Min(0)
    private int sickPay;
    @Column(name = "sickness_allowance")
    @Min(0)
    private int sicknessAllowance;
    @Column(name = "labor_fund")
    @Min(0)
    private int laborFund;
    @Column(name = "base_salary")
    @Min(0)
    private int baseSalary;
    @Column(name = "net_salary")
    @Min(0)
    private int netSalary;
    @Column(name = "seniority_bonus")
    @Min(0)
    private int seniorityBonus;
    @Column(name = "functional_bonus")
    @Min(0)
    private int functionalBonus;
    @Column(name = "incentive_pay")
    @Min(0)
    private int incentivePay;


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
        performCalculations();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getSeniorityBonus() {
        return seniorityBonus;
    }

    public void setSeniorityBonus(int seniorityBonus) {
        this.seniorityBonus = seniorityBonus;
    }

    public int getFunctionalBonus() {
        return functionalBonus;
    }

    public void setFunctionalBonus(int functionalBonus) {
        this.functionalBonus = functionalBonus;
    }

    public void setIncentivePay(int incentivePay) {
        this.incentivePay = incentivePay;
    }

    public int getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(int grossSalary) {
        this.grossSalary = grossSalary;
    }

    public int getContributionBase() {
        return contributionBase;
    }

    public void setContributionBase(int contributionBase) {
        this.contributionBase = contributionBase;
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

    public void setAccidentInsuranceContribution(int accidentInsuranceContribution) {
        this.accidentInsuranceContribution = accidentInsuranceContribution;
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

    public void setSicknessContribution(int sicknessContribution) {
        this.sicknessContribution = sicknessContribution;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public int getLaborFund() {
        return laborFund;
    }

    public void setLaborFund(int laborFund) {
        this.laborFund = laborFund;
    }

    public int getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(int netSalary) {
        this.netSalary = netSalary;
    }

    public List<SickLeave> getSickLeavesUpToMonth() {
        return sickLeavesUpToMonth;
    }

    public void setSickLeavesUpToMonth(List<SickLeave> sickLeavesUpToMonth) {
        this.sickLeavesUpToMonth = sickLeavesUpToMonth;
    }

    public List<Salary> getSalariesFromLast12Months() {
        return salariesFromLast12Months;
    }

    public void setSalariesFromLast12Months(List<Salary> salariesFromLast12Months) {
        this.salariesFromLast12Months = salariesFromLast12Months;
    }

    public List<SickLeave> getSickLeavesInMonth() {
        return sickLeavesInMonth;
    }

    public void setSickLeavesInMonth(List<SickLeave> sickLeavesInMonth) {
        this.sickLeavesInMonth = sickLeavesInMonth;
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

    public int getNumberOfSickLeaveDaysInPreviousMonth() {
        int prevmonth= month-1;
        int yearPrevMonth=year;

        if(prevmonth==0){
            yearPrevMonth=year-1;
            prevmonth=12;
        }
        int sickLeaveDaysInMonthYear = 0;
        if (!CollectionUtils.isEmpty(sickLeavesInMonth)) {
            for (SickLeave sickLeave : sickLeavesInMonth) {
                sickLeaveDaysInMonthYear += sickLeave.getNumberOfSickLeaveDaysInMonthYear(prevmonth, yearPrevMonth);
            }
        }

        return sickLeaveDaysInMonthYear;
    }

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
        if (!CollectionUtils.isEmpty(sickLeavesUpToMonth)) {
            for (SickLeave sickLeave : sickLeavesUpToMonth) {
                for (int i = 1; i < month; i++) {
                    if (sickLeave.isSickLeaveInMonthYear(i, year))
                        sickLeaveDaysUpToMonthYear += sickLeave.getNumberOfSickLeaveDaysInMonthYear(i, year);
                }
            }
        }
        return sickLeaveDaysUpToMonthYear;
    }

    public int calculateBaseComponents() {
        return calculateBaseSalary() + calculateSeniorityBonus() + calculateIncentivePay() + calculateFunctionalBonus();
    }

    public int calculateBaseSalary() {
        baseSalary = (int) Math.round(employee.getBaseSalary() * ((double) (30 - getNumberOfSickLeaveDaysInPreviousMonth()) / 30));

        return baseSalary;
    }

    public int calculateSeniorityBonus() {
        seniorityBonus = (int) Math.round(employee.getSeniorityBonus() * ((double) (30 - getNumberOfSickLeaveDaysInPreviousMonth()) / 30));

        return seniorityBonus;
    }

    public int calculateFunctionalBonus() {
        functionalBonus = (int) Math.round(employee.getFunctionalBonus() * ((double) (30 - getNumberOfSickLeaveDaysInPreviousMonth()) / 30));

        return functionalBonus;
    }

    public int calculateIncentivePay() {
        incentivePay = (int) Math.round(employee.getIncentivePay() * ((double) (30 - getNumberOfSickLeaveDaysInPreviousMonth()) / 30));

        return incentivePay;
    }


    public int calculateContributionBase() {
        contributionBase = calculateBaseComponents();

        contributionBase = Math.round(contributionBase);

        return contributionBase;
    }


    public int calculateEmployeeDeductionsFromSalary() {
        int deductionsFromSalary;

        deductionsFromSalary = incomeTaxAdvance + calculateEmployeeContribution() + calculateHealthCareContribution();
        return deductionsFromSalary;
    }

    public int calculateEmployeeContribution() {
        return calculateSicknessContribution()
                + calculatePensionContributionEmployee()
                + calculateDisabilityContributionEmployee();
    }

    public int calculatePayerDeductions() {
        return calculateDisabilityContributionPayer()
                + calculatePensionContributionPayer()
                + calculateAccidentInsuranceContribution()
                + calculateLabourFund();
    }

    public int calculateTax() {
        tax = (int) Math.round((roundToHundreds(calculateTaxBase()) * SalaryConstants.TAX_PERCENT / 100));
        return tax;
    }

    public int calculateTaxBase() {
        return grossSalary
                - pensionContributionEmployee
                - disabilityContributionEmployee
                - sicknessContribution
                - getTaxDeductibleExpenses();
    }

    public int calculateIncomeTaxAdvance() {
        incomeTaxAdvance =
                roundToHundreds(Math.round(calculateTax() - calculateHealthCareContributionDeduction() - SalaryConstants.TAX_DEDUCTION));
        return incomeTaxAdvance;
    }

    public int roundToHundreds(int number){
        int result = number/100*100;
        if (number%100>=50)
            result+=100;
        return result;
    }

    //ubezpieczenie emerytalne pracownika
    public int calculatePensionContributionEmployee() {
        pensionContributionEmployee = (int) Math.round(contributionBase * SalaryConstants.PENSION_CONTRIBUTION_EMPLOYEE_PERCENT / 100);

        return pensionContributionEmployee;
    }

    //ubezpieczenie emerytalne platnika
    public int calculatePensionContributionPayer() {
        pensionContributionPayer = (int) Math.round(contributionBase * SalaryConstants.PENSION_CONTRIBUTION_PAYER_PERCENT / 100);

        return pensionContributionPayer;
    }

    // ubezpieczenie rentowe pracownika
    public int calculateDisabilityContributionEmployee() {
        disabilityContributionEmployee = (int) Math.round(contributionBase * SalaryConstants.DISABILITY_CONTRIBUTION_EMPLOYEE_PERCENT / 100);

        return disabilityContributionEmployee;
    }

    // ubezpieczenie rentowe platnika
    public int calculateDisabilityContributionPayer() {
        disabilityContributionPayer = (int) Math.round(contributionBase * SalaryConstants.DISABILITY_CONTRIBUTION_PAYER_PERCENT / 100);

        return disabilityContributionPayer;
    }

    //ubezpieczenie wypadkowe
    public int calculateAccidentInsuranceContribution() {
        accidentInsuranceContribution = (int) Math.round(contributionBase * SalaryConstants.ACCIDENT_INSURANCE_CONTRIBUTION_PERCENT / 100);

        return accidentInsuranceContribution;
    }

    //ubezpieczenie chorobowe
    public int calculateSicknessContribution() {

        sicknessContribution = (int) Math.round(contributionBase * SalaryConstants.SICKNESS_CONTRIBUTION_PERCENT / 100);

        return sicknessContribution;
    }

    //podstawa ubezpieczenia zdrowotnego
    public int getHealthcareContributionBase() {
        return grossSalary - getPensionContributionEmployee() - getDisabilityContributionEmployee() - getSicknessContribution() - sicknessAllowance;
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
        sickPay= Math.round(calculateSickPayPerDay(sickPayBase) * (getNumberOfSickLeaveDaysInPreviousMonth()));
        return sickPay;
    }

    public int calculateSickPayPerDay(int sickPayBase){
        return (int) Math.round(Math.round((double)sickPayBase/30)*SalaryConstants.SICKPAY_RATE);
    }


    public int calculateSickPayBase() {
        int sickPayBaseSum = 0;
        int i = 0;

        if (!CollectionUtils.isEmpty(salariesFromLast12Months)) {
            List<Salary> essentialSalaries = salariesFromLast12Months.stream()
                    .filter(salary -> salary instanceof EssentialSalary)
                    .collect(Collectors.toList());

            List<Salary> overtimeSalaries = salariesFromLast12Months.stream()
                    .filter(salary -> salary instanceof OvertimeSalary)
                    .collect(Collectors.toList());

            for (Salary salary : essentialSalaries) {
                if (salary.isAllowedForSickPayBaseCalculation()) {
                    OvertimeSalary overtimeSalaryFromSameMonth =
                            (OvertimeSalary) overtimeSalaries.stream()
                                    .filter(overtimeSalary -> overtimeSalary.getMonth() == salary.month && overtimeSalary.getYear() == salary.getYear())
                                    .findFirst().orElse(new OvertimeSalary());

                    sickPayBaseSum += salary.getHealthcareContributionBase() + overtimeSalaryFromSameMonth.getHealthcareContributionBase();
                    i++;
                }
            }
        } else {
            i = 1;
            sickPayBaseSum = (int) Math.round((employee.getBaseSalary() + employee.getFunctionalBonus() + employee.getIncentivePay() + employee.getSeniorityBonus()) * SalaryConstants.SICKPAY_BASE_NET_RATE);

        }

        return (int) Math.round((double) sickPayBaseSum / i);
    }

    protected abstract boolean isAllowedForSickPayBaseCalculation();


    public int getNumberOfWorkdays() {
        int numberOfWorkDays = 0;

        int previousMonth = month == 1 ? 12 : month - 1;
        int previousMonthYear = month == 1 ? year - 1 : year;
        YearMonth yearMonth = YearMonth.of(previousMonthYear, previousMonth);
        // java 8
        List<LocalDate> freeDays =
                IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                        .mapToObj(day -> LocalDate.of(previousMonthYear, previousMonth, day))
                        .filter(this::isFreeDay)
                        .collect(Collectors.toList());

        numberOfWorkDays = yearMonth.lengthOfMonth() - freeDays.size();

        //java 11+
//        LocalDate[] weekendDays =
//                (LocalDate[]) yearMonth.atDay(1).datesUntil(yearMonth.atEndOfMonth())
//                        .filter(date -> isWeekend(date)).toArray();
//
//        numberOfWorkDays=yearMonth.lengthOfMonth()-weekendDays.length;


        return numberOfWorkDays;
    }


    public int getNumberOfWorkDaysWithSickLeave() {
        int numberOfWorkDaysWithSickLeave = 0;
        int sickLeaveMonth = month == 1 ? 12 : month - 1;
        int sickLeaveYear = month == 1 ? year - 1 : year;

        if (!CollectionUtils.isEmpty(sickLeavesInMonth)) {
            for (SickLeave sl : sickLeavesInMonth) {
                numberOfWorkDaysWithSickLeave += sl.getNumberOfSickLeaveDaysOnWorkdaysInMonthYear(sickLeaveMonth, sickLeaveYear);
            }
        }

        return numberOfWorkDaysWithSickLeave;
    }

    public boolean
    isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isFreeDay(LocalDate date) {
        return isWeekend(date) || MyDateUtils.getHolidaysInYear(year).contains(date);
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

        int sickLeaveDaysThisYear = getNumberOfSickLeaveDaysUpToMonth();

        int daysOverLimit = sickLeaveDaysThisYear - getSickLeaveLimit();

        if (daysOverLimit > 0) {
            if (daysOverLimit > getNumberOfSickLeaveDaysInPreviousMonth())
                sicknessAllowance = sickPay;
            else
                sicknessAllowance = (int) Math.round(sickPay * (((double) daysOverLimit) / getNumberOfSickLeaveDaysInPreviousMonth()));
        } else {
            sicknessAllowance = 0;
        }

        sickPay -= sicknessAllowance;

        return sicknessAllowance;
    }


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
        calculateNetSalary();
    }

    public int calculateGrossSalary() {
        grossSalary = calculateContributionBase() + sickPay + sicknessAllowance;
        return grossSalary;
    }

    public int calculateNetSalary() {
        netSalary = grossSalary - calculateEmployeeDeductionsFromSalary();
        return netSalary;
    }

    private int calculateLabourFund() {
        laborFund = (int) Math.round(contributionBase * SalaryConstants.LABOUR_FUND_PERCENT / 100);
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


    public String toString() {
        return "Salary{" +
                ", id=" + id +
                ", month=" + month +
                ", year=" + year +
                "grossSalary=" + grossSalary +
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

                ", tax=" + tax +
                ", incomeTaxAdvance=" + incomeTaxAdvance +
                ", taxDeductibleExpenses=" + taxDeductibleExpenses +
                ", sickPay=" + sickPay +
                ", sicknessAllowance=" + sicknessAllowance +
                ", laborFund=" + laborFund +
                ", baseSalary=" + baseSalary +
                '}';
    }


    public String getBaseSalaryString() {

        double bs = baseSalary / 100.0;

        return String.format("%.2f", bs);
    }


    public String getGrossSalaryString() {

        double gs = grossSalary / 100.0;

        return String.format("%.2f", gs);
    }

    public String getNetSalaryString() {

        double ns = netSalary / 100.0;

        return String.format("%.2f", ns);
    }

    public String getSicknessContributionString() {

        double sc = sicknessContribution / 100.0;

        return String.format("%.2f", sc);
    }

    public String getAccidentInsuranceContributionString() {

        double aic = accidentInsuranceContribution / 100.0;

        return String.format("%.2f", aic);
    }

    public String getPensionContributionEmployeeString() {

        double pce = pensionContributionEmployee / 100.0;

        return String.format("%.2f", pce);
    }

    public String getPensionContributionPayerString() {

        double pcp = pensionContributionPayer / 100.0;

        return String.format("%.2f", pcp);
    }

    public String getDisabilityContributionEmployeeString() {

        double dce = disabilityContributionEmployee / 100.0;

        return String.format("%.2f", dce);
    }

    public String getDisabilityContributionPayerString() {

        double dcp = disabilityContributionPayer / 100.0;

        return String.format("%.2f", dcp);
    }

    public String getFunctionalBonusString() {

        double fb = functionalBonus / 100.0;

        return String.format("%.2f", fb);
    }

    public String getIncentivePayString() {

        double ip = incentivePay / 100.0;

        return String.format("%.2f", ip);
    }

    public String getSeniorityBonusString() {

        double sb = seniorityBonus / 100.0;

        return String.format("%.2f", sb);
    }

    public String getSickPayString() {

        double sp = sickPay / 100.0;

        return String.format("%.2f", sp);
    }

    public String getSicknessAllowanceString() {

        double sa = sicknessAllowance / 100.0;

        return String.format("%.2f", sa);
    }

    public String getIncomeTaxAdvanceString() {

        double ita = incomeTaxAdvance / 100.0;

        return String.format("%.2f", ita);
    }

    public String getHealthcareContributionString() {

        double hc = healthcareContribution / 100.0;

        return String.format("%.2f", hc);
    }

    public String getHealthcareContributionDeductionString() {

        double hcd = healthcareContributionDeduction / 100.0;

        return String.format("%.2f", hcd);
    }

    public String getTaxDeductibleExpensesString() {

        double tde = taxDeductibleExpenses / 100.0;

        return String.format("%.2f", tde);
    }

    public String getLaborFundString() {

        double lf = laborFund / 100.0;

        return String.format("%.2f", lf);
    }

}

