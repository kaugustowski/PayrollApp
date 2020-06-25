package pl.wizyg.payroll.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MyDateUtilsTest {


    @Test
    void getEasterDayInYear2015() {

        LocalDate expected = LocalDate.of(2015, 4, 5);
        LocalDate actual = MyDateUtils.getEasterDayInYear(2015);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getEasterDayInYear2020() {
        LocalDate expected = LocalDate.of(2020, 4, 12);
        LocalDate actual = MyDateUtils.getEasterDayInYear(2020);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getEasterDayInYear2021() {
        LocalDate expected = LocalDate.of(2021, 4, 4);
        LocalDate actual = MyDateUtils.getEasterDayInYear(2021);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getEasterDayInYear2049() {
        LocalDate expected = LocalDate.of(2049, 4, 18);
        LocalDate actual = MyDateUtils.getEasterDayInYear(2049);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getEasterDayInYear1994() {
        LocalDate expected = LocalDate.of(1994, 4, 3);
        LocalDate actual = MyDateUtils.getEasterDayInYear(1994);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getCorpusChristiDayInYear2015() {
        LocalDate expected = LocalDate.of(2015, 6, 4);
        LocalDate actual = MyDateUtils.getCorpusChristiDayInYear(2015);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getCorpusChristiDayInYear2049() {
        LocalDate expected = LocalDate.of(2049, 6, 17);
        LocalDate actual = MyDateUtils.getCorpusChristiDayInYear(2049);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getCorpusChristiDayInYear2012() {
        LocalDate expected = LocalDate.of(2012, 6, 7);
        LocalDate actual = MyDateUtils.getCorpusChristiDayInYear(2012);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getCorpusChristiDayInYear2031() {
        LocalDate expected = LocalDate.of(2031, 6, 12);
        LocalDate actual = MyDateUtils.getCorpusChristiDayInYear(2031);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void areDateRangesOverlapping() {

        LocalDate startdate1 = LocalDate.of(2020, 5, 1);
        LocalDate enddate1 = LocalDate.of(2020, 5, 5);
        LocalDate startdate2 = LocalDate.of(2020, 5, 3);
        LocalDate enddate2 = LocalDate.of(2020, 5, 6);

        assertThat(MyDateUtils.isOverlapped(startdate1, enddate1, startdate2, enddate2)).isEqualTo(true);

    }

    @Test
    void areDateRangesOverlapping2() {

        LocalDate startdate1 = LocalDate.of(2020, 5, 1);
        LocalDate enddate1 = LocalDate.of(2020, 5, 6);
        LocalDate startdate2 = LocalDate.of(2020, 5, 5);
        LocalDate enddate2 = LocalDate.of(2020, 5, 5);

        assertThat(MyDateUtils.isOverlapped(startdate1, enddate1, startdate2, enddate2)).isEqualTo(true);

    }

    @Test
    void areDateRangesOverlapping3() {

        LocalDate startdate1 = LocalDate.of(2020, 5, 1);
        LocalDate enddate1 = LocalDate.of(2020, 5, 5);
        LocalDate startdate2 = LocalDate.of(2020, 5, 5);
        LocalDate enddate2 = LocalDate.of(2020, 5, 5);

        assertThat(MyDateUtils.isOverlapped(startdate1, enddate1, startdate2, enddate2)).isEqualTo(true);

    }

    @Test
    void areDateRangesOverlapping4() {

        LocalDate startdate1 = LocalDate.of(2020, 5, 1);
        LocalDate enddate1 = LocalDate.of(2020, 5, 1);
        LocalDate startdate2 = LocalDate.of(2020, 5, 1);
        LocalDate enddate2 = LocalDate.of(2020, 5, 1);

        assertThat(MyDateUtils.isOverlapped(startdate1, enddate1, startdate2, enddate2)).isEqualTo(true);

    }

    @Test
    void areDateRangesOverlapping5() {

        LocalDate startdate1 = LocalDate.of(2020, 4, 29);
        LocalDate enddate1 = LocalDate.of(2020, 5, 5);
        LocalDate startdate2 = LocalDate.of(2020, 5, 5);
        LocalDate enddate2 = LocalDate.of(2020, 5, 8);

        assertThat(MyDateUtils.isOverlapped(startdate1, enddate1, startdate2, enddate2)).isEqualTo(true);

    }

    @Test
    void areDateRangesOverlapping6() {

        LocalDate startdate1 = LocalDate.of(2020, 4, 28);
        LocalDate enddate1 = LocalDate.of(2020, 5, 4);
        LocalDate startdate2 = LocalDate.of(2020, 5, 5);
        LocalDate enddate2 = LocalDate.of(2020, 5, 8);

        assertThat(MyDateUtils.isOverlapped(startdate1, enddate1, startdate2, enddate2)).isEqualTo(false);

    }

    @Test
    void areDateRangesOverlapping7() {

        LocalDate startdate1 = LocalDate.of(2020, 4, 29);
        LocalDate enddate1 = LocalDate.of(2020, 5, 5);
        LocalDate startdate2 = LocalDate.of(2020, 5, 7);
        LocalDate enddate2 = LocalDate.of(2020, 5, 15);

        assertThat(MyDateUtils.isOverlapped(startdate1, enddate1, startdate2, enddate2)).isEqualTo(false);

    }

}