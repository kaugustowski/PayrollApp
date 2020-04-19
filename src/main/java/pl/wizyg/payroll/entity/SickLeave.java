package pl.wizyg.payroll.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "sick_leave")
public class SickLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sickLeaveId;

    @Column(name = "sick_leave_from")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate sickLeaveFrom;

    @Column(name = "sick_leave_to")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate sickLeaveTo;

    @Transient
    private int consecutiveDays;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public SickLeave() {
    }

    public int getConsecutiveDays() {
        consecutiveDays =
                Period.between(sickLeaveFrom, sickLeaveTo).getDays() + 1;

        return consecutiveDays;
    }

    public int getSickLeaveId() {
        return sickLeaveId;
    }

    public void setSickLeaveId(int sickLeaveId) {
        this.sickLeaveId = sickLeaveId;
    }

    public LocalDate getSickLeaveFrom() {
        return sickLeaveFrom;
    }

    public void setSickLeaveFrom(LocalDate sickLeaveFrom) {
        this.sickLeaveFrom = sickLeaveFrom;
    }

    public LocalDate getSickLeaveTo() {
        return sickLeaveTo;
    }


    public void setSickLeaveTo(LocalDate sickLeaveTo) {
        this.sickLeaveTo = sickLeaveTo;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
