package pl.wizyg.payroll.entity;

import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Component
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {

    @Column(name = "base_salary")
    int baseSalary;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "seniority_bonus")
    private int seniorityBonus;
    @Column(name = "functional_bonus")
    private int functionalBonus;
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "pesel")
    @PESEL
    private String pesel;
    @Column(name = "email")
    private String email;
    @Column(name = "birth_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @Column(name = "employeed_on_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate employeedOnDate;
    @Column(name = "is_allowed_for_extra_tax_deductible_expenses")
    private boolean isAllowedForExtraTaxDeductibleExpenses;

    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "employee")
    private List<SickLeave> sickLeaves;

    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
//    @OneToMany(mappedBy = "employee")
    //   private List<EmploymentHistory> employmentHistoryList;

    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
//    @OneToMany(mappedBy = "employee")
//    private Set<Salary> salaries;

//    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "employee")
//    private Set<Overtime> overtimeList;

    public void addSickLeave(SickLeave sickLeave) {
        if (sickLeaves == null) {
            sickLeaves = new ArrayList<>();
        }
        sickLeaves.add(sickLeave);
        sickLeave.setEmployee(this);
    }

//    public void addTeachingPractice(EmploymentHistory employmentHistory) {
//        if (employmentHistoryList == null) {
//            employmentHistoryList = new ArrayList<>();
//        }
//        employmentHistoryList.add(employmentHistory);
//        employmentHistory.setEmployee(this);
//    }
//
//    public void addSalary(Salary salary) {
//        if (salaries == null) {
//            salaries = new HashSet<>();
//        }
//        salaries.add(salary);
//        salary.setEmployee(this);
//    }
//
//    public void addOvertime(Overtime overtime) {
//        if (salaries == null) {
//            salaries = new HashSet<>();
//        }
//        overtimeList.add(overtime);
//        overtime.setEmployee(this);
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getSeniorityBonus() {
        return seniorityBonus;
    }

    public void setSeniorityBonus(int seniorityBonus) {
        this.seniorityBonus = seniorityBonus;
    }

    public int getFunctionalBonus() {
        return functionalBonus;
    }

    public void setFunctionalBonus(int functionalBonus) {
        this.functionalBonus = functionalBonus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getEmployeedOnDate() {
        return employeedOnDate;
    }

    public void setEmployeedOnDate(LocalDate employeedOnDate) {
        this.employeedOnDate = employeedOnDate;
    }

    public boolean isAllowedForExtraTaxDeductibleExpenses() {
        return isAllowedForExtraTaxDeductibleExpenses;
    }

    public void setAllowedForExtraTaxDeductibleExpenses(boolean allowedForExtraTaxDeductibleExpenses) {
        isAllowedForExtraTaxDeductibleExpenses = allowedForExtraTaxDeductibleExpenses;
    }

    public int getIncentivePay() {
        return 0;
    }

}
