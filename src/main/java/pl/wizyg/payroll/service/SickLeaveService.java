package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.SickLeave;

import java.util.List;


public interface SickLeaveService {

    List<SickLeave> getEmployeeSickLeavesMonthYear(int teacherId, int mont, int year);

    List<SickLeave> getEmployeeSickLeavesUpToMonthInYear(int employeeId, int month, int year);

    List<SickLeave> getEmployeesSickLeaves(int employeeId);

   // void saveSickLeave(SickLeave sickLeave);

    void saveSickLeave(SickLeave sickLeave, int EmployeeId);
}
