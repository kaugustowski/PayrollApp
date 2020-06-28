package pl.wizyg.payroll.helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyDateUtils {

    public static List<LocalDate> getHolidaysInYear(int year) {
        List<LocalDate> holidays = new ArrayList<>();

        LocalDate newYear = LocalDate.of(year, 1, 1);
        holidays.add(newYear);

        LocalDate epiphany = LocalDate.of(year, 1, 6);
        holidays.add(epiphany);

        LocalDate labourDay = LocalDate.of(year, 5, 1);
        holidays.add(labourDay);

        LocalDate constitutionDay = LocalDate.of(year, 5, 3);
        holidays.add(constitutionDay);

        LocalDate allSaintsDay = LocalDate.of(year, 11, 1);
        holidays.add(allSaintsDay);

        LocalDate independenceDay = LocalDate.of(year, 11, 11);
        holidays.add(independenceDay);

        LocalDate christmasDay1 = LocalDate.of(year, 12, 25);
        holidays.add(christmasDay1);

        LocalDate christmasDay2 = LocalDate.of(year, 12, 26);
        holidays.add(christmasDay2);

        LocalDate easterDay1 = getEasterDayInYear(year);
        holidays.add(easterDay1);

        LocalDate easterDay2 = easterDay1.plusDays(1);
        holidays.add(easterDay2);

        LocalDate corpusChristi = getCorpusChristiDayInYear(year);
        holidays.add(corpusChristi);

        return holidays;
    }

    public static LocalDate getEasterDayInYear(int year) {

        int a = year % 19;

        int b = year / 100;

        int c = year % 100;

        int d = b / 4;

        int e = b % 4;

        int f = (b + 8) / 25;

        int g = (b - f + 1) / 3;

        int h = (19 * a + b - d - g + 15) % 30;

        int i = c / 4;

        int k = c % 4;

        int l = (32 + 2 * e + 2 * i - h - k) % 7;

        int m = (a + 11 * h + 22 * l) / 451;

        int z = (h + l - 7 * m + 114);

        int p = z % 31;

        int easterDay = p + 1;

        int easterMonth = z / 31;


        return LocalDate.of(year, easterMonth, easterDay);
    }

    public static LocalDate getCorpusChristiDayInYear(int year) {
        return getEasterDayInYear(year).plusDays(60);
    }


    public static boolean isOverlapped(LocalDate start1, LocalDate end1,
                                       LocalDate start2, LocalDate end2) {
        return (start1.isBefore(end2) && end1.isAfter(start2)
                || start1.equals(end2)
                || start2.equals(end1));

    }


}
