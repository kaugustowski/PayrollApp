package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryService {

    List<EmploymentHistory> getEmploymentHistoryListForEmployee(int employeeId);

    void saveEmploymentHistory(EmploymentHistory employmentHistory, int employeeId);

    void deleteEmploymentHistory(EmploymentHistory employmentHistory);

    void deleteEmploymentHistory(int empHistoryId);
}