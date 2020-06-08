package pl.wizyg.payroll.service;

import org.springframework.stereotype.Service;
import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.repository.OvertimeRepository;

import java.util.List;

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
        employeeService.getEmployee(employeeId).addOvertime(overtime);
        overtimeRepository.save(overtime);
    }

    @Override
    public List<Overtime> getEmployeeOvertimeList(int employeeId) {
        return overtimeRepository.findAllByEmployee_Id(employeeId);
    }

}
