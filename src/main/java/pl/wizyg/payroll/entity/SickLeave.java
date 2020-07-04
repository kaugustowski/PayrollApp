package pl.wizyg.payroll.entity;

import org.springframework.format.annotation.DateTimeFormat;
import pl.wizyg.payroll.helper.MyDateUtils;
import pl.wizyg.payroll.validator.ValidSickLeaveDates;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "sick_leave")
@ValidSickLeaveDates(message = "Data zakończenia zwolnienia nie może poprzedzać daty jego rozpoczęcia")
public class SickLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sickLeaveId;

    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public SickLeave() {
    }

    public SickLeave(Employee employee) {
        this.employee = employee;
    }

    public int getConsecutiveDays() {
        return (int) DAYS.between(startDate, endDate) + 1;
    }


    public int getNumberOfSickLeaveDaysInMonthYear(int month, int year) {

        int sickLeaveDays = 0;

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate beginningOfMonth = yearMonth.atDay(1);

        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        // zaczyna sie przed poczatkiem miesiaca i konczy po koncu miesiaca
        if (startDate.isBefore(beginningOfMonth) && endDate.isAfter(endOfMonth)) {
            sickLeaveDays = yearMonth.lengthOfMonth();
            // zaczyna sie po poczatku miesiaca i konczy po koncu miesiaca
        }  else if (startDate.isAfter(beginningOfMonth) && endDate.isAfter((endOfMonth))) {

            sickLeaveDays = (int) DAYS.between(startDate, endOfMonth) + 1;
            // zaczyna sie przed poczatkiem miesiaca i konczy przed koncem miesiaca
        } else if (startDate.isBefore(beginningOfMonth) && endDate.isBefore((endOfMonth))) {
            sickLeaveDays = (int) DAYS.between(beginningOfMonth, endDate) + 1;
            // zaczyna sie przed poczatkiem i konczy po koncu miesiaca
        } else if (startDate.isBefore(beginningOfMonth) && endDate.isAfter((endOfMonth))) {
            sickLeaveDays = 30;
        } else {
            sickLeaveDays = (int) DAYS.between(startDate, endDate) + 1;
        }
        return sickLeaveDays;
    }

    public boolean isSickLeaveInMonthYear(int month, int year) {

        boolean result;

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate beginningOfMonth = yearMonth.atDay(1);

        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        result = MyDateUtils.isOverlapped(startDate, endDate, beginningOfMonth, endOfMonth);

        return result;
    }

    public int getNumberOfSickLeaveDaysOnWorkdaysInMonthYear(int month, int year) {

        int numberOfSickleaveDaysOnWorkdays = 0;

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate beginningOfMonth = yearMonth.atDay(1);

        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        // zaczyna sie przed poczatkiem miesiaca i konczy po koncu miesiaca
        if (startDate.isBefore(beginningOfMonth) && endDate.isAfter(endOfMonth)) {
            numberOfSickleaveDaysOnWorkdays =
                     IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                            .mapToObj(day -> LocalDate.of(year, month, day))
                            .filter(date ->!isFreeDay(date)).toArray().length;
            // zaczyna sie w trakcie miesiaca i konczy przed koncem miesiaca
        } else if (startDate.isAfter(beginningOfMonth) && endDate.isBefore(endOfMonth)) {
            numberOfSickleaveDaysOnWorkdays =
                     IntStream.rangeClosed(startDate.getDayOfMonth(), endDate.getDayOfMonth())
                            .mapToObj(day -> LocalDate.of(year, month, day))
                            .filter(date ->!isFreeDay(date)).toArray().length;
            // zaczyna sie po poczatku miesiaca i konczy  po koncu miesiaca
        } else if (startDate.isAfter(beginningOfMonth) && endDate.isAfter((endOfMonth))) {
            numberOfSickleaveDaysOnWorkdays =
                     IntStream.rangeClosed(startDate.getDayOfMonth(), yearMonth.lengthOfMonth())
                            .mapToObj(day -> LocalDate.of(year, month, day))
                            .filter(date ->!isFreeDay(date)).toArray().length;
            // zaczyna sie przed poczatkiem i konczy przed koncem
        } else if (startDate.isBefore(beginningOfMonth) && endDate.isBefore((endOfMonth))) {
            numberOfSickleaveDaysOnWorkdays =  IntStream.rangeClosed(1, endDate.getDayOfMonth())
                    .mapToObj(day -> LocalDate.of(year, month, day))
                    .filter(date -> !isFreeDay(date)).toArray().length;
        } else {
            numberOfSickleaveDaysOnWorkdays = IntStream.rangeClosed(startDate.getDayOfMonth(), endDate.getDayOfMonth())
                    .mapToObj(day -> LocalDate.of(year, month, day))
                    .filter(date -> !isFreeDay(date)).toArray().length;
        }

        return numberOfSickleaveDaysOnWorkdays;
    }

    public int getSickLeaveId() {
        return sickLeaveId;
    }

    public void setSickLeaveId(int sickLeaveId) {
        this.sickLeaveId = sickLeaveId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void validateAndSwapIfNeeded() {
        if (endDate != null && startDate != null)
            if (endDate.isBefore(startDate)) {
                LocalDate tempDate = this.endDate;
                this.endDate = this.startDate;
                this.startDate = tempDate;
            }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "SickLeave{" +
                "sickLeaveId=" + sickLeaveId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employee=" + employee +
                '}';
    }

    public boolean
    isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY;

    }

    public boolean isFreeDay(LocalDate date) {
        return isWeekend(date) || MyDateUtils.getHolidaysInYear(date.getYear()).contains(date);
    }
}
