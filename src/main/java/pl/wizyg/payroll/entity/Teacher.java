package pl.wizyg.payroll.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Component
@Table(name = "Teacher")
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @NotNull
    @Column(name = "first_name")
    private String lastName;

    @Column(name = "first_name")
    private int pesel;

    @Column(name = "base_salary")
    private double baseSalary;

    @Column(name = "seniorty_bonus")
    private double seniorityBonus;

    @Column(name = "incentive_pay")
    private double incentivePay;

    @Column(name = "salary")
    private double salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_type")
    private TeacherType teacherType;

    @Column(name = "email")
    private String email;

    public Teacher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getSeniorityBonus() {
        return seniorityBonus;
    }

    public void setSeniorityBonus(double seniorityBonus) {
        this.seniorityBonus = seniorityBonus;
    }

    public double getIncentivePay() {
        return incentivePay;
    }

    public void setIncentivePay(double incentivePay) {
        this.incentivePay = incentivePay;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
