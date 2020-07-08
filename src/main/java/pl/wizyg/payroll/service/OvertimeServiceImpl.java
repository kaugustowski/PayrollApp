package pl.wizyg.payroll.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.repository.OvertimeRepository;

import java.util.List;

@Transactional
@Service
public class OvertimeServiceImpl implements OvertimeService {

    final
    OvertimeRepository overtimeRepository;

    final
    EmployeeService employeeService;

    public OvertimeServiceImpl(OvertimeRepository overtimeRepository,EmployeeService employeeService) {
        this.overtimeRepository = overtimeRepository;
        this.employeeService=employeeService;
    }


    @Override
    public void saveOvertime(Overtime overtime, int employeeId) {

        Overtime ot = overtimeRepository.findByEmployee_IdAndMonthAndYear(employeeId, overtime.getMonth(), overtime.getYear());

        if (ot != null) {
            ot.setNumberOfOverTimeHoursInCurrentMonth(overtime.getNumberOfOverTimeHoursInCurrentMonth());
            overtimeRepository.save(ot);
        } else {
            employeeService.getEmployee(employeeId).addOvertime(overtime);
            overtimeRepository.save(overtime);
        }
    }

    @Override
    public List<Overtime> getEmployeeOvertimeList(int employeeId) {
        return overtimeRepository.findAllByEmployee_Id(employeeId);
    }

    @Override
    public Overtime getEmployeeOvertimeInMonthYear(int employeeId, int month, int year) {
        return overtimeRepository.findByEmployee_IdAndMonthAndYear(employeeId, month, year);
    }

    @Override
    public void delete(int overtimeId) {

        overtimeRepository.delete(getOvertime(overtimeId));
    }

    @Override
    public Overtime getOvertime(int overtimeId) {
        return overtimeRepository.findById(overtimeId).get();
    }

}
