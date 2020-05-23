package pl.wizyg.payroll.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.YearMonth;

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
        System.out.println("Default Constructor");
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
        } else if (startDate.isAfter(beginningOfMonth) && endDate.isBefore(endOfMonth)) {
            sickLeaveDays = (int) DAYS.between(startDate, endDate) + 1;
        } else if (startDate.isAfter(beginningOfMonth) && endDate.isAfter((endOfMonth))) {
            sickLeaveDays = (int) DAYS.between(startDate, endOfMonth) + 1;
        } else if (startDate.isBefore(beginningOfMonth) && endDate.isBefore((endOfMonth))) {
            sickLeaveDays = (int) DAYS.between(beginningOfMonth, endDate) + 1;
        } else {
            sickLeaveDays = 30;
        }
        return sickLeaveDays;
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
}
