package pl.wizyg.payroll.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "teacher")
public class Teacher extends Employee implements FullTimeEmployee {

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_type")
    private TeacherType teacherType;

    @Column(name = "incentive_pay")
    private double incentivePay;

    @Column(name = "education")
    private Education education;

    public Teacher() {
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
}
