package pl.wizyg.payroll.entity;

import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {


    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "base_salary")
    int baseSalary;
    @Column(name = "seniority_bonus")
    @Min(value = 0, message = "Wartość nie może być ujemna!")
    private int seniorityBonus;
    @Column(name = "functional_bonus")
    @Min(value = 0, message = "Wartość nie może być ujemna!")
    private int functionalBonus;
    @Column(name = "first_name")
    @NotNull(message = "Pole wymagane")
    private String firstName;
    @NotNull(message = "Pole wymagane")
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "pesel", unique = true)
    @PESEL(message = "niepoprawny numer PESEL")
    private String pesel;
    @Column(name = "email", unique = true)
    @Email
    private String email;
    @Column(name = "birth_date")
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @Column(name = "employeed_on_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate employeedOnDate;
    @Column(name = "is_allowed_for_extra_tax_deductible_expenses")
    private boolean isAllowedForExtraTaxDeductibleExpenses;
    @Column(name = "active")
    private boolean active;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "employee")
    private List<SickLeave> sickLeaves;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "employee")
    private List<EmploymentHistory> employmentHistoryList;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "employee")
    private Set<Salary> salaries;


    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "employee")
    private Set<Overtime> overtimeSet;

    public void addSickLeave(SickLeave sickLeave) {
        if (sickLeaves == null) {
            sickLeaves = new ArrayList<>();
        }
        sickLeaves.add(sickLeave);
        sickLeave.setEmployee(this);
    }

    public void addEmploymentHistory(EmploymentHistory employmentHistory) {
        if (employmentHistoryList == null) {
            employmentHistoryList = new ArrayList<>();
        }
        employmentHistoryList.add(employmentHistory);
        employmentHistory.setEmployee(this);
    }

    public void addSalary(Salary salary) {
        if (salaries == null) {
            salaries = new HashSet<>();
        }
        salaries.add(salary);
        salary.setEmployee(this);
    }

    public void addOvertime(Overtime overtime) {
        if (overtimeSet == null) {
            overtimeSet = new HashSet<>();
        }
        overtimeSet.add(overtime);
        overtime.setEmployee(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<SickLeave> getSickLeaves() {
        return sickLeaves;
    }

    public void setSickLeaves(List<SickLeave> sickLeaves) {
        this.sickLeaves = sickLeaves;
    }

    public List<EmploymentHistory> getEmploymentHistoryList() {
        return employmentHistoryList;
    }

    public void setEmploymentHistoryList(List<EmploymentHistory> employmentHistoryList) {
        this.employmentHistoryList = employmentHistoryList;
    }

    public Set<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(Set<Salary> salaries) {
        this.salaries = salaries;
    }

    public Set<Overtime> getOvertimeList() {
        return overtimeSet;
    }

    public void setOvertimeList(Set<Overtime> overtimeSet) {
        this.overtimeSet = overtimeSet;
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

    public boolean isOver55YearsOld(LocalDate date) {
        boolean isOver55;
        isOver55 = birthDate.plusYears(55).isBefore(date);
        return isOver55;
    }

    public boolean isOver18onEmployeedDate() {
        return !employeedOnDate.isBefore(birthDate.plusYears(18));
    }


    public boolean isOver55inYear(int year) {
        return (year > (birthDate.getYear() + 55));
    }

    public int getIncentivePay() {
        return 0;
    }

    public abstract int getOvertimeHourRate();

    public void setSeniorityBonusString (String senBonus){

        seniorityBonus = (int) Math.round(Double.parseDouble(senBonus)*100);
    }
    public String getSeniorityBonusString(){

        double sb = (double)seniorityBonus/100;

        return String.format(Locale.ROOT,"%.2f",sb);
    }

    public void setFunctionalBonusString (String senBonus){

        functionalBonus = (int) Math.round(Double.parseDouble(senBonus) * 100);
    }

    public String getFunctionalBonusString() {

        double sb = (double) functionalBonus / 100;

        return String.format(Locale.ROOT, "%.2f", sb);
    }

    public String getBaseSalaryString() {

        double bs = (double) getBaseSalary() / 100;

        return String.format(Locale.ROOT, "%.2f", bs);
    }
}

