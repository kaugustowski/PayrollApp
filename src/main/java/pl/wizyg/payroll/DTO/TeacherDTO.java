package pl.wizyg.payroll.DTO;

import pl.wizyg.payroll.entity.Education;
import pl.wizyg.payroll.entity.TeacherType;

import java.time.LocalDate;

public class TeacherDTO {

    private Integer id;

    private int baseSalary;

    private int seniorityBonus;

    private int functionalBonus;

    private String firstName;

    private String lastName;

    private String pesel;

    private String email;

    private LocalDate birthDate;

    private LocalDate employeedOnDate;

    private boolean isAllowedForExtraTaxDeductibleExpenses;

    private Education education;

    private TeacherType teacherType;

    public TeacherDTO(Integer id,
                      int baseSalary,
                      int seniorityBonus,
                      int functionalBonus,
                      String firstName,
                      String lastName,
                      String pesel,
                      String email,
                      LocalDate birthDate,
                      LocalDate employeedOnDate,
                      boolean isAllowedForExtraTaxDeductibleExpenses,
                      Education education,
                      TeacherType teacherType) {
        this.id = id;
        this.baseSalary = baseSalary;
        this.seniorityBonus = seniorityBonus;
        this.functionalBonus = functionalBonus;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.email = email;
        this.birthDate = birthDate;
        this.employeedOnDate = employeedOnDate;
        this.isAllowedForExtraTaxDeductibleExpenses = isAllowedForExtraTaxDeductibleExpenses;
        this.education = education;
        this.teacherType = teacherType;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public TeacherType getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
