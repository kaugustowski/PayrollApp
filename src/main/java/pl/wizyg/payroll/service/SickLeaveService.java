package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.SickLeave;

import java.util.List;

public interface SickLeaveService {

    List<SickLeave> getTeachersSickLeavesMonthYear(int teacherId, int mont, int year);

    void addSickLeave(Integer teacherId, SickLeave sickLeave);

}
