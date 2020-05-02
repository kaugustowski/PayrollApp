package pl.wizyg.payroll;

import pl.wizyg.payroll.entity.SickLeave;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        SickLeave sickLeave = new SickLeave();
        LocalDate startDate = LocalDate.of(2020, 4, 2);
        LocalDate endDate = LocalDate.of(2020, 4, 12);
        sickLeave.setSickLeaveFrom(startDate);
        sickLeave.setSickLeaveTo(endDate);

        int expected1 = 10;
        int actual1 = sickLeave.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        System.out.println("expected1 = " + expected1);
        System.out.println("actual1 = " + actual1);


        SickLeave sickLeave2 = new SickLeave();
        LocalDate startDate2 = LocalDate.of(2020, 3, 27);
        LocalDate endDate2 = LocalDate.of(2020, 5, 2);
        sickLeave2.setSickLeaveFrom(startDate2);
        sickLeave2.setSickLeaveTo(endDate2);

        int expected2 = 30;
        int actual2 = sickLeave2.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        System.out.println("expected2 = " + expected2);
        System.out.println("actual2 = " + actual2);

        SickLeave sickLeave3 = new SickLeave();
        LocalDate startDate3 = LocalDate.of(2020, 3, 27);
        LocalDate endDate3 = LocalDate.of(2020, 4, 5);
        sickLeave3.setSickLeaveFrom(startDate3);
        sickLeave3.setSickLeaveTo(endDate3);

        int expected3 = 5;
        int actual3 = sickLeave3.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        System.out.println("expected3 = " + expected3);
        System.out.println("actual3 = " + actual3);

        SickLeave sickLeave4 = new SickLeave();
        LocalDate startDate4 = LocalDate.of(2020, 4, 27);
        LocalDate endDate4 = LocalDate.of(2020, 5, 2);
        sickLeave4.setSickLeaveFrom(startDate4);
        sickLeave4.setSickLeaveTo(endDate4);

        int expected4 = 6;
        int actual4 = sickLeave4.getNumberOfSickLeaveDaysInMonthYear(4, 2020);

        System.out.println("expected4 = " + expected4);
        System.out.println("actual4 = " + actual4);
    }
}
