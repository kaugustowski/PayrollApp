package pl.wizyg.payroll.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class OvertimeSalaryTest {

    Teacher employee = new Teacher() {
    };
    Teacher employee2 = new Teacher() {
    };

    @BeforeEach
    void init() {
        employee.setBirthDate(LocalDate.of(1980, 1, 1));
        employee.setBaseSalary(325000);
        employee.setTeacherType(TeacherType.APPOINTED);
        employee.setEducation(Education.HIGHER_WITH_PEDAGOGIC_PREP);
        employee2.setBirthDate(LocalDate.of(1980, 1, 1));
        employee2.setBaseSalary(325000);
        employee2.setTeacherType(TeacherType.INTERN);
        employee2.setEducation(Education.HIGHER_WITH_PEDAGOGIC_PREP);
    }

    @Test
    void calculateContributionBaseOvertime() {
    }

    @Test
    void calculateGrossSalaryOvertime() {
    }

    @Test
    void calculateTax() {
    }

    @Test
    void calculateTaxBase() {
    }


    @Test
    void performCalculations10Hours() {

        Overtime ot = new Overtime(employee, 5, 2020, 10);
        employee.addOvertime(ot);
        Salary salary = new OvertimeSalary(employee, 5, 2020);

        assertThat(salary.getGrossSalary()).isEqualTo(43330);
        assertThat(salary.getContributionBase()).isEqualTo(43330);
        assertThat(salary.getAccidentInsuranceContribution()).isEqualTo(403);
        assertThat(salary.getSicknessContribution()).isEqualTo(1062);
        assertThat(salary.getDisabilityContributionEmployee()).isEqualTo(650);
        assertThat(salary.getDisabilityContributionPayer()).isEqualTo(2816);
        assertThat(salary.getPensionContributionEmployee()).isEqualTo(4229);
        assertThat(salary.getPensionContributionPayer()).isEqualTo(4229);
        assertThat(salary.getNetSalary()).isEqualTo(30524);
        assertThat(salary.getHealthcareContribution()).isEqualTo(3365);
        assertThat(salary.getIncomeTaxAdvance()).isEqualTo(3500);
        assertThat(salary.getTax()).isEqualTo(6358);

    }

    @Test
    void performCalculations12HoursIntern() {

        Overtime ot = new Overtime(employee2, 5, 2020, 12);
        employee2.addOvertime(ot);
        Salary salary = new OvertimeSalary(employee2, 5, 2020);

        assertThat(salary.getGrossSalary()).isEqualTo(44508);
        assertThat(salary.getContributionBase()).isEqualTo(44508);
        assertThat(salary.getAccidentInsuranceContribution()).isEqualTo(414);
        assertThat(salary.getSicknessContribution()).isEqualTo(1090);
        assertThat(salary.getDisabilityContributionEmployee()).isEqualTo(668);
        assertThat(salary.getDisabilityContributionPayer()).isEqualTo(2893);
        assertThat(salary.getPensionContributionEmployee()).isEqualTo(4344);
        assertThat(salary.getPensionContributionPayer()).isEqualTo(4344);
        assertThat(salary.getHealthcareContribution()).isEqualTo(3457);
        assertThat(salary.getIncomeTaxAdvance()).isEqualTo(3600);
        assertThat(salary.getTax()).isEqualTo(6528);
        assertThat(salary.getNetSalary()).isEqualTo(31349);

    }

    @Test
    void performCalculations15hours() {

        Overtime ot = new Overtime(employee, 5, 2020, 15);
        employee.addOvertime(ot);
        Salary salary = new OvertimeSalary(employee, 5, 2020);

        assertThat(salary.getGrossSalary()).isEqualTo(64995);
        assertThat(salary.getContributionBase()).isEqualTo(64995);
        assertThat(salary.getAccidentInsuranceContribution()).isEqualTo(604);
        assertThat(salary.getSicknessContribution()).isEqualTo(1592);
        assertThat(salary.getDisabilityContributionEmployee()).isEqualTo(975);
        assertThat(salary.getDisabilityContributionPayer()).isEqualTo(4225);
        assertThat(salary.getPensionContributionEmployee()).isEqualTo(6344);
        assertThat(salary.getPensionContributionPayer()).isEqualTo(6344);
        assertThat(salary.getHealthcareContribution()).isEqualTo(5048);
        assertThat(salary.getIncomeTaxAdvance()).isEqualTo(5200);
        assertThat(salary.getTax()).isEqualTo(9537);
        assertThat(salary.getNetSalary()).isEqualTo(45836);

    }

    @Test
    void performCalculations12hours() {

        Overtime ot = new Overtime(employee, 5, 2020, 12);
        employee.addOvertime(ot);
        Salary salary = new OvertimeSalary(employee, 5, 2020);

        assertThat(salary.getGrossSalary()).isEqualTo(51996);
        assertThat(salary.getContributionBase()).isEqualTo(51996);
        assertThat(salary.getAccidentInsuranceContribution()).isEqualTo(484);
        assertThat(salary.getSicknessContribution()).isEqualTo(1274);
        assertThat(salary.getDisabilityContributionEmployee()).isEqualTo(780);
        assertThat(salary.getDisabilityContributionPayer()).isEqualTo(3380);
        assertThat(salary.getPensionContributionEmployee()).isEqualTo(5075);
        assertThat(salary.getPensionContributionPayer()).isEqualTo(5075);
        assertThat(salary.getHealthcareContribution()).isEqualTo(4038);
        assertThat(salary.getIncomeTaxAdvance()).isEqualTo(4200);
        assertThat(salary.getTax()).isEqualTo(7633);
        assertThat(salary.getNetSalary()).isEqualTo(36629);

    }
}