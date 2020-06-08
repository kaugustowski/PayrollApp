package pl.wizyg.payroll.entity;

import javax.persistence.*;

@Entity
@Table(name = "overtime")
public class Overtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "overtime_hours")
    private int numberOfOverTimeHoursInCurrentMonth;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Overtime(){};

    public Overtime(Employee employee) {
        this.employee=employee;
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
