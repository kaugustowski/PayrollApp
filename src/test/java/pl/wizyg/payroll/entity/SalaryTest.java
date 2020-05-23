package pl.wizyg.payroll.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SalaryTest {

    @Test
    void isWeekendOnSaturday() {
        Salary salary = new EssentialSalary();
        LocalDate.of(2020, 5, 23);
        boolean expected = true;
        boolean actual = salary.isWeekend(LocalDate.of(2020, 5, 23));
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


}