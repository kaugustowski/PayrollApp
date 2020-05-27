package pl.wizyg.payroll.entity;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SickLeaveTest {


    @Test
    void getConsecutiveDays() {

    }

    @Test
    void getNumberOfSickLeaveDaysInMonthYearInsideMonth() {
        SickLeave sickLeave = new SickLeave();
        LocalDate startDate = LocalDate.of(2020, 4, 2);
        LocalDate endDate = LocalDate.of(2020, 4, 12);
        sickLeave.setStartDate(startDate);
        sickLeave.setEndDate(endDate);

        int expected1 = 11;
        int actual1 = sickLeave.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void getNumberOfSickLeaveDaysInMonthYearOutsideMonth() {
        SickLeave sickLeave2 = new SickLeave();
        LocalDate startDate2 = LocalDate.of(2020, 3, 27);
        LocalDate endDate2 = LocalDate.of(2020, 5, 2);
        sickLeave2.setStartDate(startDate2);
        sickLeave2.setEndDate(endDate2);

        int expected2 = 30;
        int actual2 = sickLeave2.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual2).isEqualTo(expected2);
    }


    @Test
    void getNumberOfSickLeaveDaysInMonthYearStartBefore() {

        SickLeave sickLeave3 = new SickLeave();
        LocalDate startDate3 = LocalDate.of(2020, 3, 27);
        LocalDate endDate3 = LocalDate.of(2020, 4, 5);
        sickLeave3.setStartDate(startDate3);
        sickLeave3.setEndDate(endDate3);

        int expected3 = 5;
        int actual3 = sickLeave3.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual3).isEqualTo(expected3);
    }

    @Test
    void getNumberOfSickLeaveDaysInMonthYearEndAfter() {

        SickLeave sickLeave4 = new SickLeave();
        LocalDate startDate4 = LocalDate.of(2020, 4, 27);
        LocalDate endDate4 = LocalDate.of(2020, 5, 2);
        sickLeave4.setStartDate(startDate4);
        sickLeave4.setEndDate(endDate4);

        int expected4 = 4;
        int actual4 = sickLeave4.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual4).isEqualTo(expected4);
    }

    @Test
    void getNumberOfSickLeaveDaysInMonthYearOneDay() {

        SickLeave sickLeave5 = new SickLeave();
        LocalDate startDate5 = LocalDate.of(2020, 4, 25);
        LocalDate endDate5 = LocalDate.of(2020, 4, 25);
        sickLeave5.setStartDate(startDate5);
        sickLeave5.setEndDate(endDate5);

        int expected5 = 1;
        int actual5 = sickLeave5.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual5).isEqualTo(expected5);
    }

    @Test
    void getNumberOfSickLeaveDaysOnWorkdaysInMonthYear() {
        SickLeave sickLeave6 = new SickLeave();
        LocalDate startDate6 = LocalDate.of(2020, 4, 25);
        LocalDate endDate6 = LocalDate.of(2020, 5, 10);
        sickLeave6.setStartDate(startDate6);
        sickLeave6.setEndDate(endDate6);

        int expected = 5;
        int actual = sickLeave6.getNumberOfSickLeaveDaysOnWorkdaysInMonthYear(5,2020);

        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getNumberOfSickLeaveDaysOnWorkdaysInMonthYear2() {
        SickLeave sickLeave7 = new SickLeave();
        LocalDate startDate7 = LocalDate.of(2020, 6, 3);
        LocalDate endDate7 = LocalDate.of(2020, 6, 12);
        sickLeave7.setStartDate(startDate7);
        sickLeave7.setEndDate(endDate7);

        int expected = 7;
        int actual = sickLeave7.getNumberOfSickLeaveDaysOnWorkdaysInMonthYear(6,2020);

        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getNumberOfSickLeaveDaysOnWorkdaysInMonthYear3() {
        SickLeave sickLeave7 = new SickLeave();
        LocalDate startDate7 = LocalDate.of(2020, 5, 30);
        LocalDate endDate7 = LocalDate.of(2020, 6, 22);
        sickLeave7.setStartDate(startDate7);
        sickLeave7.setEndDate(endDate7);

        int expected = 15;
        int actual = sickLeave7.getNumberOfSickLeaveDaysOnWorkdaysInMonthYear(6,2020);

        assertThat(actual).isEqualTo(expected);
    }

}