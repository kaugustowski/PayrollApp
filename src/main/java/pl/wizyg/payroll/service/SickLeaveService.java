package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.exception.SickLeaveNotFoundException;
import pl.wizyg.payroll.exception.SickLeavesOverlapException;

import java.util.List;


public interface SickLeaveService {

    List<SickLeave> getEmployeeSickLeavesMonthYear(int teacherId, int mont, int year);

    List<SickLeave> getEmployeeSickLeavesUpToMonthInYear(int employeeId, int month, int year);

    List<SickLeave> getEmployeesSickLeaves(int employeeId);

    // void saveSickLeave(SickLeave sickLeave);

    void saveSickLeave(SickLeave sickLeave, int EmployeeId) throws SickLeavesOverlapException;

    SickLeave getSickLeave(int sickLeaveId) throws SickLeaveNotFoundException;

    void delete(int sickLeaveId) throws SickLeaveNotFoundException;
}
