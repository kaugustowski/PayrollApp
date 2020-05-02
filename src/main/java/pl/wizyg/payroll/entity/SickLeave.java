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

    @Transient
    private int consecutiveDays;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public SickLeave() {
    }

    public int getConsecutiveDays() {
        consecutiveDays =
                (int) DAYS.between(startDate, endDate) + 1;

        return consecutiveDays;
    }

    public int getNumberOfSickLeaveDaysInMonthYear(int month, int year) {

        int sickLeaveDays = 0;

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate begginingOfMonth = yearMonth.atDay(1);

        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        if (startDate.isBefore(begginingOfMonth) && endDate.isAfter(endOfMonth)) {
            sickLeaveDays = yearMonth.lengthOfMonth();
        } else if (startDate.isAfter(begginingOfMonth) && endDate.isBefore(endOfMonth)) {
            sickLeaveDays = (int) DAYS.between(startDate, endDate) + 1;
        } else if (startDate.isAfter(begginingOfMonth) && endDate.isAfter((endOfMonth))) {
            sickLeaveDays = (int) DAYS.between(startDate, endOfMonth) + 1;
        } else if (startDate.isBefore(begginingOfMonth) && endDate.isBefore((endOfMonth))) {
            sickLeaveDays = (int) DAYS.between(begginingOfMonth, endDate) + 1;
        } else {
            sickLeaveDays = 45;
        }
        return sickLeaveDays;
    }

    public int getSickLeaveId() {
        return sickLeaveId;
    }

    public void setSickLeaveId(int sickLeaveId) {
        this.sickLeaveId = sickLeaveId;
    }

    public LocalDate getSickLeaveFrom() {
        return startDate;
    }

    public void setSickLeaveFrom(LocalDate sickLeaveFrom) {
        this.startDate = sickLeaveFrom;
    }

    public LocalDate getSickLeaveTo() {
        return endDate;
    }


    public void setSickLeaveTo(LocalDate sickLeaveTo) {
        this.endDate = sickLeaveTo;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
