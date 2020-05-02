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
        sickLeave.setSickLeaveFrom(startDate);
        sickLeave.setSickLeaveTo(endDate);

        int expected1 = 11;
        int actual1 = sickLeave.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    void getNumberOfSickLeaveDaysInMonthYearOutsideMonth() {
        SickLeave sickLeave2 = new SickLeave();
        LocalDate startDate2 = LocalDate.of(2020, 3, 27);
        LocalDate endDate2 = LocalDate.of(2020, 5, 2);
        sickLeave2.setSickLeaveFrom(startDate2);
        sickLeave2.setSickLeaveTo(endDate2);

        int expected2 = 30;
        int actual2 = sickLeave2.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual2).isEqualTo(expected2);
    }


    @Test
    void getNumberOfSickLeaveDaysInMonthYearStartBefore() {

        SickLeave sickLeave3 = new SickLeave();
        LocalDate startDate3 = LocalDate.of(2020, 3, 27);
        LocalDate endDate3 = LocalDate.of(2020, 4, 5);
        sickLeave3.setSickLeaveFrom(startDate3);
        sickLeave3.setSickLeaveTo(endDate3);

        int expected3 = 5;
        int actual3 = sickLeave3.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual3).isEqualTo(expected3);
    }

    @Test
    void getNumberOfSickLeaveDaysInMonthYearEndAfter() {

        SickLeave sickLeave4 = new SickLeave();
        LocalDate startDate4 = LocalDate.of(2020, 4, 27);
        LocalDate endDate4 = LocalDate.of(2020, 5, 2);
        sickLeave4.setSickLeaveFrom(startDate4);
        sickLeave4.setSickLeaveTo(endDate4);

        int expected4 = 4;
        int actual4 = sickLeave4.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual4).isEqualTo(expected4);
    }

    @Test
    void getNumberOfSickLeaveDaysInMonthYearOneDay() {

        SickLeave sickLeave5 = new SickLeave();
        LocalDate startDate5 = LocalDate.of(2020, 4, 25);
        LocalDate endDate5 = LocalDate.of(2020, 4, 25);
        sickLeave5.setSickLeaveFrom(startDate5);
        sickLeave5.setSickLeaveTo(endDate5);

        int expected5 = 1;
        int actual5 = sickLeave5.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        assertThat(actual5).isEqualTo(expected5);
    }

}