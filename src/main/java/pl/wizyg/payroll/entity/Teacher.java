package pl.wizyg.payroll.entity;

import pl.wizyg.payroll.helper.SalaryConstants;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Locale;

@Entity
@Table(name = "teacher")
public class Teacher extends Employee {

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_type")
    private TeacherType teacherType;

    @Min(value = 0, message = "Wartość nie może być ujemna!")
    @Column(name = "incentive_pay")
    private int incentivePay;

    @Enumerated(EnumType.STRING)
    @Column(name = "education")
    private Education education;


////    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    //   @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "employee")
    //   private List<SickLeave> sickLeaves;
//
//    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
//    @OneToMany(mappedBy = "teacher")
//    private List<EmploymentHistory> employmentHistoryList;
//
//    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
//    @OneToMany(mappedBy = "teacher")
//    private List<Salary> salaries;

//    @ManyToMany
//    @JoinTable(
//            name="Teacher_Payroll",
//            joinColumns = {@JoinColumn(name = "teacher_id")},
//            inverseJoinColumns = {@JoinColumn(name="payroll_id")}
//    )
//    private List<Payroll> payrollList;
//public void addSickLeave(SickLeave sickLeave) {
//    if (sickLeaves == null) {
//        sickLeaves = new ArrayList<>();
//    }
//    sickLeaves.add(sickLeave);
//    sickLeave.setEmployee(this);
//}

    public Teacher() {
    }


    public int getBaseSalary() {
        if (education == Education.HIGHER_WITH_PEDAGOGIC_PREP) {
            if (teacherType == TeacherType.INTERN)
                baseSalary = 278200;
            if (teacherType == TeacherType.CONTRACT)
                baseSalary = 286200;
            if (teacherType == TeacherType.APPOINTED)
                baseSalary = 325000;
            if (teacherType == TeacherType.CERTIFIED)
                baseSalary = 381700;
        }
        if (education == Education.HIGHER_WITHOUT_PEDAGOGIC_PREP) {
            if (teacherType == TeacherType.INTERN)
                baseSalary = 261700;
            if (teacherType == TeacherType.CONTRACT)
                baseSalary = 266300;
            if (teacherType == TeacherType.APPOINTED)
                baseSalary = 283200;
            if (teacherType == TeacherType.CERTIFIED)
                baseSalary = 332400;
        }
        if (education == Education.OTHER) {
            if (teacherType == TeacherType.INTERN)
                baseSalary = 260000;
            if (teacherType == TeacherType.CONTRACT)
                baseSalary = 261700;
            if (teacherType == TeacherType.APPOINTED)
                baseSalary = 263800;
            if (teacherType == TeacherType.CERTIFIED)
                baseSalary = 290500;
        }
        return baseSalary;
    }

    public TeacherType getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }

    public int getIncentivePay() {
        return incentivePay;
    }

    public void setIncentivePay(int incentivePay) {
        this.incentivePay = incentivePay;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    @Override
    public int getOvertimeHourRate() {
        return (int) ((double) getBaseSalary() / SalaryConstants.TEACHER_OVERTIME_COEFFICIENT); //TODO sprawdzic ile ma byc
    }

    public void setIncentivePayString (String senBonus){

        incentivePay = (int) Math.round(Double.parseDouble(senBonus)*100);
    }
    public String getIncentivePayString(){

        double sb = (double)incentivePay/100;

        return String.format(Locale.ROOT,"%.2f",sb);
    }

}
