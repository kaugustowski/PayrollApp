package pl.wizyg.payroll.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class HolidaysTest {


    @Test
    void getEasterDayInYear2015() {

        LocalDate expected = LocalDate.of(2015, 4, 5);
        LocalDate actual = Holidays.getEasterDayInYear(2015);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getEasterDayInYear2020() {
        LocalDate expected = LocalDate.of(2020, 4, 12);
        LocalDate actual = Holidays.getEasterDayInYear(2020);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getEasterDayInYear2021() {
        LocalDate expected = LocalDate.of(2021, 4, 4);
        LocalDate actual = Holidays.getEasterDayInYear(2021);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getEasterDayInYear2049() {
        LocalDate expected = LocalDate.of(2049, 4, 18);
        LocalDate actual = Holidays.getEasterDayInYear(2049);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getEasterDayInYear1994() {
        LocalDate expected = LocalDate.of(1994, 4, 3);
        LocalDate actual = Holidays.getEasterDayInYear(1994);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getCorpusChristiDayInYear2015() {
        LocalDate expected = LocalDate.of(2015, 6, 4);
        LocalDate actual = Holidays.getCorpusChristiDayInYear(2015);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getCorpusChristiDayInYear2049() {
        LocalDate expected = LocalDate.of(2049, 6, 17);
        LocalDate actual = Holidays.getCorpusChristiDayInYear(2049);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getCorpusChristiDayInYear2012() {
        LocalDate expected = LocalDate.of(2012, 6, 7);
        LocalDate actual = Holidays.getCorpusChristiDayInYear(2012);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void getCorpusChristiDayInYear2031() {
        LocalDate expected = LocalDate.of(2031, 6, 12);
        LocalDate actual = Holidays.getCorpusChristiDayInYear(2031);
        assertThat(expected).isEqualTo(actual);
    }
}