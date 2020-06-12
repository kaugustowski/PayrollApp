package pl.wizyg.payroll.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SalaryTest {

    private final Teacher employee = new Teacher() {
    @Override
    public int getOvertimeHourRate() {
        return 0;
    }

};




    @Test
    void isWeekendOnSaturday() {
        Salary salary = new EssentialSalary();
        LocalDate.of(2020, 5, 23);
        boolean expected = true;
        boolean actual = salary.isWeekend(LocalDate.of(2020, 5, 23));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void isWeekendOnSunday() {
        Salary salary = new EssentialSalary();
        LocalDate.of(2020, 5, 23);
        boolean actual = salary.isWeekend(LocalDate.of(2020, 5, 24));
        assertThat(actual).isEqualTo(true);

    }

    @Test
    void isWeekendOnMonday() {
        Salary salary = new EssentialSalary();
        boolean actual = salary.isWeekend(LocalDate.of(2020, 5, 25));
        assertThat(actual).isEqualTo(false);

    }

    @Test
    void isWeekendOnFriday() {
        Salary salary = new EssentialSalary();
        boolean actual = salary.isWeekend(LocalDate.of(2020, 5, 22));
        assertThat(actual).isEqualTo(false);

    }

    @Test
    void isWeekendOnWednesday() {
        Salary salary = new EssentialSalary();
        boolean actual = salary.isWeekend(LocalDate.of(2020, 5, 20));
        assertThat(actual).isEqualTo(false);

    }

    @Test
    void isFreeDay1stOfMay() {
        Salary salary = new EssentialSalary();
        salary.setYear(2020);
        boolean actual = salary.isFreeDay(LocalDate.of(2020, 5, 1));
        assertThat(actual).isEqualTo(true);
    }

    @Test
    void isFreeDayEaster2ndDay() {
        Salary salary = new EssentialSalary();
        salary.setYear(2020);
        boolean actual = salary.isFreeDay(LocalDate.of(2020, 4, 13));
        assertThat(actual).isEqualTo(true);
    }


    @BeforeEach
    void init(){
        employee.setBirthDate(LocalDate.of(1980,1,1));
        employee.setBaseSalary(325000);
        employee.setFunctionalBonus(20000);
        employee.setIncentivePay(20000);
        employee.setSeniorityBonus(30000);

    }


    @Test
    void calculateGrossSalaryNoSickLeave() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        int expected = 395000;
        int actual = salary.calculateGrossSalary();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateTax() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        salary.setGrossSalary(395000);
        salary.setTaxDeductibleExpenses(25000);
        salary.setPensionContributionEmployee(38552);
        salary.setDisabilityContributionEmployee(5925);
        salary.setSicknessContribution(9678);

        int expected = 53686;
        int actual = salary.calculateTax();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateTaxBase() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        salary.setGrossSalary(395000);
        salary.setTaxDeductibleExpenses(25000);
        salary.setPensionContributionEmployee(38552);
        salary.setDisabilityContributionEmployee(5925);
        salary.setSicknessContribution(9678);

        int expected = 315845;
        int actual = salary.calculateTaxBase();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateIncomeTaxAdvance() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        salary.setGrossSalary(395000);
        salary.setTaxDeductibleExpenses(25000);
        salary.setPensionContributionEmployee(38552);
        salary.setDisabilityContributionEmployee(5925);
        salary.setSicknessContribution(9678);

        int expected = 22900;
        int actual = salary.calculateIncomeTaxAdvance();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculatePensionContributionEmployee() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        int expected = 38552;
        int actual = salary.calculatePensionContributionEmployee();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculatePensionContributionPayer() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        int expected = 38552;
        int actual = salary.calculatePensionContributionPayer();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateDisabilityContributionEmployee() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        int expected = 5925;
        int actual = salary.calculateDisabilityContributionEmployee();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateDisabilityContributionPayer() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        int expected = 25675;
        int actual = salary.calculateDisabilityContributionPayer();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateAccidentInsuranceContribution() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        int expected = 3674;
        int actual = salary.calculateAccidentInsuranceContribution();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateSicknessContribution() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        int expected = 9678;
        int actual = salary.calculateSicknessContribution();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateHealthCareContribution() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setGrossSalary(395000);
        salary.setContributionBase(395000);
        int expected = 30676;
        int actual = salary.calculateHealthCareContribution();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateHealthCareContributionDeduction() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setContributionBase(395000);
        salary.setGrossSalary(395000);
        int expected = 26415;
        int actual = salary.calculateHealthCareContributionDeduction();
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getNetSalary() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setGrossSalary(395000);
        salary.setContributionBase(395000);
        salary.setIncomeTaxAdvance(22900);
        int expected = 287269;
        int actual = salary.getNetSalary();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculatewithround(){
        int actual= (int) (315800*17.0/100);
        int expected = 53686;
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void calculateEmployeeDeductionsFromSalary() {
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setGrossSalary(395000);
        salary.setContributionBase(395000);
        salary.setIncomeTaxAdvance(22900);
        int expected = 107731;
        int actual = salary.calculateEmployeeDeductionsFromSalary();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateSalary(){
        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);

        salary.performCalculations();

        assertThat(salary.getContributionBase()).isEqualTo(395000);
        assertThat(salary.getPensionContributionEmployee()).isEqualTo(38552);
        assertThat(salary.getDisabilityContributionEmployee()).isEqualTo(5925);
        assertThat(salary.getSicknessContribution()).isEqualTo(9678);
        assertThat(salary.getAccidentInsuranceContribution()).isEqualTo(3674);
        assertThat(salary.getPensionContributionPayer()).isEqualTo(38552);
        assertThat(salary.getDisabilityContributionPayer()).isEqualTo(25675);
        assertThat(salary.getHealthcareContribution()).isEqualTo(30676);
        assertThat(salary.getIncomeTaxAdvance()).isEqualTo(22900);
        assertThat(salary.getGrossSalary()).isEqualTo(395000);
        assertThat(salary.getNetSalary()).isEqualTo(287269);

    }

    @Test
    void calculateSalary2(){
        Salary salary = new EssentialSalary();
        Teacher employee2 = new Teacher() {
            @Override
            public int getOvertimeHourRate() {
                return 0;
            }

        };
        employee2.setBirthDate(LocalDate.of(1960,1,1));
        employee2.setBaseSalary(381700);
        employee2.setFunctionalBonus(20000);
        employee2.setIncentivePay(30000);
        employee2.setSeniorityBonus(76340);
        salary.setEmployee(employee2);

        salary.performCalculations();

        assertThat(salary.getContributionBase()).isEqualTo(508040);
        assertThat(salary.getPensionContributionEmployee()).isEqualTo(49585);
        assertThat(salary.getDisabilityContributionEmployee()).isEqualTo(7621);
        assertThat(salary.getSicknessContribution()).isEqualTo(12447);
        assertThat(salary.getAccidentInsuranceContribution()).isEqualTo(4725);
        assertThat(salary.getPensionContributionPayer()).isEqualTo(49585);
        assertThat(salary.getDisabilityContributionPayer()).isEqualTo(33023);
        assertThat(salary.getHealthcareContribution()).isEqualTo(39455);
        assertThat(salary.getIncomeTaxAdvance()).isEqualTo(31900);
        assertThat(salary.getGrossSalary()).isEqualTo(508040);
        assertThat(salary.getNetSalary()).isEqualTo(367032);
    }

    @Test
    void calculateSalary3(){
        Salary salary = new EssentialSalary();
        Teacher employee3 = new Teacher() {
        };
        employee3.setBirthDate(LocalDate.of(1990,1,1));
        employee3.setBaseSalary(260000);
        employee3.setFunctionalBonus(20000);
        employee3.setIncentivePay(10000);
        employee3.setSeniorityBonus(0);
        salary.setEmployee(employee3);

        salary.performCalculations();

        assertThat(salary.getContributionBase()).isEqualTo(290000);
        assertThat(salary.getPensionContributionEmployee()).isEqualTo(28304);
        assertThat(salary.getDisabilityContributionEmployee()).isEqualTo(4350);
        assertThat(salary.getSicknessContribution()).isEqualTo(7105);
        assertThat(salary.getAccidentInsuranceContribution()).isEqualTo(2697);
        assertThat(salary.getPensionContributionPayer()).isEqualTo(28304);
        assertThat(salary.getDisabilityContributionPayer()).isEqualTo(18850);
        assertThat(salary.getHealthcareContribution()).isEqualTo(22522);
        assertThat(salary.getIncomeTaxAdvance()).isEqualTo(14500);
        assertThat(salary.getGrossSalary()).isEqualTo(290000);
        assertThat(salary.getNetSalary()).isEqualTo(213219);
    }


    @Test
    void calculateSalaryWithSickLeave(){

        SickLeave sl1 = new SickLeave(employee);
        sl1.setEndDate(LocalDate.of(2020,4,10));
        sl1.setStartDate(LocalDate.of(2020,4,1));

        List<SickLeave> sickLeavesInPrevMonth = new ArrayList<>();
        sickLeavesInPrevMonth.add(sl1);

        Salary salary = new EssentialSalary();
        salary.setEmployee(employee);
        salary.setMonth(5);
        salary.setYear(2020);
        salary.setSickLeavesInMonth(sickLeavesInPrevMonth);

        salary.performCalculations();

        assertThat(salary.getContributionBase()).isEqualTo(263333);

        assertThat(salary.getHealthcareContribution()).isEqualTo(28632);
        assertThat(salary.getIncomeTaxAdvance()).isEqualTo(20800);
        assertThat(salary.getPensionContributionEmployee()).isEqualTo(25701);
        assertThat(salary.getPensionContributionPayer()).isEqualTo(25701);
        assertThat(salary.getDisabilityContributionEmployee()).isEqualTo(3950);
        assertThat(salary.getDisabilityContributionPayer()).isEqualTo(17117);
        assertThat(salary.getAccidentInsuranceContribution()).isEqualTo(2449);
        assertThat(salary.getSicknessContribution()).isEqualTo(6452);
        assertThat(salary.calculateSickPayBase()).isEqualTo(340846);
        assertThat(salary.calculateSickPay(340846)).isEqualTo(90900);
        assertThat(salary.getNetSalary()).isEqualTo(268698);


    }
}