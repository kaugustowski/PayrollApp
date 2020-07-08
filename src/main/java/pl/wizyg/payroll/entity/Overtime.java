package pl.wizyg.payroll.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "overtime")
public class Overtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "year")
    private int year;

    @Range(min = 1, max = 12, message = "Miesiąc powinien mieścić się w przedziale 1-12")
    @Column(name = "month")
    private int month;

    @Column(name = "overtime_hours")
    @Positive(message = "Wartość musi być dodatnia")
    private int numberOfOverTimeHoursInCurrentMonth;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Overtime() {
    }

    public Overtime(Employee employee) {
        this.employee = employee;
    }

    public Overtime(Employee employee, int month, int year, int overtimeHours) {
        this.employee = employee;
        this.month = month;
        this.year = year;
        this.numberOfOverTimeHoursInCurrentMonth = overtimeHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNumberOfOverTimeHoursInCurrentMonth() {
        return numberOfOverTimeHoursInCurrentMonth;
    }

    public void setNumberOfOverTimeHoursInCurrentMonth(int numberOfOverTimeHoursInCurrentMonth) {
        this.numberOfOverTimeHoursInCurrentMonth = numberOfOverTimeHoursInCurrentMonth;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
