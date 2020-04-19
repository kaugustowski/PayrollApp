package pl.wizyg.payroll.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends Employee {

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_type")
    private TeacherType teacherType;

    @Column(name = "incentive_pay")
    private double incentivePay;

    @Enumerated(EnumType.STRING)
    @Column(name = "education")
    private Education education;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "teacher")
private List<SickLeave> sickLeaves;

    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    @OneToMany(mappedBy = "teacher")
    private List<TeachingPractice> teachingPracticeList;

    //    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    @OneToMany(mappedBy = "teacher")
    private List<Salary> salaries;

//    @ManyToMany
//    @JoinTable(
//            name="Teacher_Payroll",
//            joinColumns = {@JoinColumn(name = "teacher_id")},
//            inverseJoinColumns = {@JoinColumn(name="payroll_id")}
//    )
//    private List<Payroll> payrollList;


    public Teacher() {
    }

    public void addSickLeave(SickLeave sickLeave) {

        if (sickLeaves == null) {
            sickLeaves = new ArrayList<>();
        }

        sickLeaves.add(sickLeave);
        sickLeave.setTeacher(this);
    }

    public void addTeachingPractice(TeachingPractice teachingPractice) {

        if (teachingPracticeList == null) {
            teachingPracticeList = new ArrayList<>();
        }

        teachingPracticeList.add(teachingPractice);
        teachingPractice.setTeacher(this);

    }

    public void addSalary(Salary salary) {

        if (salaries == null) {
            salaries = new ArrayList<>();
        }

        salaries.add(salary);
        salary.setTeacher(this);

    }


    public void setBaseSalary() {
        if (education == Education.HIGHER_WITH_PEDAGOGIC_PREP) {
            if (teacherType == TeacherType.INTERN)
                baseSalary = 2782;
            if (teacherType == TeacherType.CONTRACT)
                baseSalary = 2862;
            if (teacherType == TeacherType.APPOINTED)
                baseSalary = 3250;
            if (teacherType == TeacherType.CERTIFIED)
                baseSalary = 3817;
        }
        if (education == Education.HIGHER_WITHOUT_PEDAGOGIC_PREP) {
            if (teacherType == TeacherType.INTERN)
                baseSalary = 2617;
            if (teacherType == TeacherType.CONTRACT)
                baseSalary = 2663;
            if (teacherType == TeacherType.APPOINTED)
                baseSalary = 2832;
            if (teacherType == TeacherType.CERTIFIED)
                baseSalary = 3324;
        }
        if (education == Education.OTHER) {
            if (teacherType == TeacherType.INTERN)
                baseSalary = 2600;
            if (teacherType == TeacherType.CONTRACT)
                baseSalary = 2617;
            if (teacherType == TeacherType.APPOINTED)
                baseSalary = 2638;
            if (teacherType == TeacherType.CERTIFIED)
                baseSalary = 2905;
        }
    }

    public TeacherType getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(TeacherType teacherType) {
        this.teacherType = teacherType;
    }

    public double getIncentivePay() {
        return incentivePay;
    }

    public void setIncentivePay(double incentivePay) {
        this.incentivePay = incentivePay;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    @Override
    public double calculateSalary() {

        salary = baseSalary + incentivePay + seniorityBonus;

        return salary;
    }

    public List<SickLeave> getSickLeaves() {
        return sickLeaves;
    }

    public void setSickLeaves(List<SickLeave> sickLeaves) {
        this.sickLeaves = sickLeaves;
    }

    public List<TeachingPractice> getTeachingPracticeList() {
        return teachingPracticeList;
    }

    public void setTeachingPracticeList(List<TeachingPractice> teachingPracticeList) {
        this.teachingPracticeList = teachingPracticeList;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }
}
