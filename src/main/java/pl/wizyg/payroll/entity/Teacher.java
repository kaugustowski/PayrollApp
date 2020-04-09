package pl.wizyg.payroll.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
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

    @OneToMany(mappedBy = "teacher")
    private List<SickLeave> sickLeaves;

    @OneToMany(mappedBy = "teacher")
    private List<TeachingPractice> teachingPracticeList;

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

    public void add(SickLeave sickLeave) {

        if (sickLeaves == null) {
            sickLeaves = new ArrayList<>();
        }

        sickLeaves.add(sickLeave);
        sickLeave.setTeacher(this);
    }

    public void add(TeachingPractice teachingPractice) {

        if (teachingPracticeList == null) {
            teachingPracticeList = new ArrayList<>();
        }

        teachingPracticeList.add(teachingPractice);
        teachingPractice.setTeacher(this);

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
}
