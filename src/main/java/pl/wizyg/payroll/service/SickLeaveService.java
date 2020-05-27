package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.SickLeave;

import java.util.List;

public interface SickLeaveService {

    List<SickLeave> getEmployeesSickLeavesMonthYear(int teacherId, int mont, int year);

    void addSickLeave(Integer teacherId, SickLeave sickLeave);

}
