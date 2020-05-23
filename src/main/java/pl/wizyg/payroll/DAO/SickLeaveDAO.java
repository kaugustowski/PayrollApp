package pl.wizyg.payroll.DAO;

import pl.wizyg.payroll.entity.SickLeave;

public interface SickLeaveDAO {


    void addSickLeave(Integer employeeId, SickLeave sickLeave);

}
