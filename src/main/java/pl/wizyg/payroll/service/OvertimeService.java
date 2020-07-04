package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Overtime;

import java.util.List;

public interface OvertimeService {

    void saveOvertime(Overtime overtime, int employeeId);

    List<Overtime> getEmployeeOvertimeList(int employeeId);

    Overtime getEmployeeOvertimeInMonthYear(int employeeId, int month, int year);
}
