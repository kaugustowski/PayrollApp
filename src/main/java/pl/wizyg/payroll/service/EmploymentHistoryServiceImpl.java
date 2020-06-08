package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wizyg.payroll.entity.EmploymentHistory;
import pl.wizyg.payroll.repository.EmploymentHistoryRepository;

import java.util.List;

@Service
public class EmploymentHistoryServiceImpl implements EmploymentHistoryService {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmploymentHistoryRepository employmentHistoryRepository;

    public EmploymentHistory getEmploymentHistory(int id){
        return employmentHistoryRepository.findById(id).get();
    }

    @Override
    public List<EmploymentHistory> getEmploymentHistoryListForEmployee(int employeeId){
        return employmentHistoryRepository.findAllByEmployee_Id(employeeId);
    }

    @Override
    public void saveEmploymentHistory(EmploymentHistory employmentHistory, int employeeId) {
        employeeService.getEmployee(employeeId).addEmploymentHistory(employmentHistory);
        employmentHistoryRepository.save(employmentHistory);
    }

    @Override
    public void deleteEmploymentHistory(EmploymentHistory employmentHistory){
        employmentHistoryRepository.delete(employmentHistory);
    }



}
