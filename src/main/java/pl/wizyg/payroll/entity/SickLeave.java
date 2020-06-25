package pl.wizyg.payroll.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Table(name = "sick_leave")
public class SickLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sickLeaveId;


    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @Column(name = "end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;


    //    @Transient
//    private int consecutiveDays;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public SickLeave() {
    }

    public SickLeave(Employee employee) {
        this.employee = employee;
    }

    public int getConsecutiveDays() {
        // consecutiveDays
        return (int) DAYS.between(startDate, endDate) + 1;
    }


    public int getNumberOfSickLeaveDaysInMonthYear(int month, int year) {

        int sickLeaveDays = 0;

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate beginningOfMonth = yearMonth.atDay(1);

        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        if (startDate.isBefore(beginningOfMonth) && endDate.isAfter(endOfMonth)) {
            sickLeaveDays = yearMonth.lengthOfMonth();
        }  else if (startDate.isAfter(beginningOfMonth) && endDate.isAfter((endOfMonth))) {
            sickLeaveDays = (int) DAYS.between(startDate, endOfMonth) + 1;
        } else if (startDate.isBefore(beginningOfMonth) && endDate.isBefore((endOfMonth))) {
            sickLeaveDays = (int) DAYS.between(beginningOfMonth, endDate) + 1;
        } else if (startDate.isBefore(beginningOfMonth) && endDate.isAfter((endOfMonth))){
            sickLeaveDays = 30;
        }
        else {
            sickLeaveDays = (int) DAYS.between(startDate, endDate) + 1;
        }
        return sickLeaveDays;
    }

    public int getNumberOfSickLeaveDaysOnWorkdaysInMonthYear(int month, int year){

        int numberOfSickleaveDaysOnWorkdays = 0;

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate beginningOfMonth = yearMonth.atDay(1);

        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        if (startDate.isBefore(beginningOfMonth) && endDate.isAfter(endOfMonth)) {
            numberOfSickleaveDaysOnWorkdays =
                     IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                            .mapToObj(day -> LocalDate.of(year, month, day))
                            .filter(date ->!isFreeDay(date)).toArray().length;
        } else if (startDate.isAfter(beginningOfMonth) && endDate.isBefore(endOfMonth)) {
            numberOfSickleaveDaysOnWorkdays =
                     IntStream.rangeClosed(startDate.getDayOfMonth(), endDate.getDayOfMonth())
                            .mapToObj(day -> LocalDate.of(year, month, day))
                            .filter(date ->!isFreeDay(date)).toArray().length;
        } else if (startDate.isAfter(beginningOfMonth) && endDate.isAfter((endOfMonth))) {
            numberOfSickleaveDaysOnWorkdays =
                     IntStream.rangeClosed(startDate.getDayOfMonth(), yearMonth.lengthOfMonth())
                            .mapToObj(day -> LocalDate.of(year, month, day))
                            .filter(date ->!isFreeDay(date)).toArray().length;
        } else if (startDate.isBefore(beginningOfMonth) && endDate.isBefore((endOfMonth))) {
            numberOfSickleaveDaysOnWorkdays =  IntStream.rangeClosed(1, endDate.getDayOfMonth())
                    .mapToObj(day -> LocalDate.of(year, month, day))
                    .filter(date -> !isFreeDay(date)).toArray().length;
        } else {
            System.out.println("??????????");
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
