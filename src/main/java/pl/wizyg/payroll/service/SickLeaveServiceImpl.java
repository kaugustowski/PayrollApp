package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.entity.MyDateUtils;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.exception.SickLeaveNotFoundException;
import pl.wizyg.payroll.exception.SickLeavesOverlapException;
import pl.wizyg.payroll.repository.SickLeaveRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@Transactional
public class SickLeaveServiceImpl implements SickLeaveService {

    SickLeaveRepository sickLeaveRepository;

    EmployeeService employeeService;


    @Autowired
    public SickLeaveServiceImpl(SickLeaveRepository sickLeaveRepository,EmployeeService employeeService) {
        this.sickLeaveRepository = sickLeaveRepository;
        this.employeeService=employeeService;
    }

    @Override
    public List<SickLeave> getEmployeeSickLeavesMonthYear(int employeeId, int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfNextMonth = yearMonth.plusMonths(1).atDay(1);
        LocalDate lastDayOfPreviousMonth = yearMonth.minusMonths(1).atEndOfMonth();

        List<SickLeave> sickLeaves = sickLeaveRepository.findAllByEmployeeIdAndStartDateBeforeAndEndDateAfter(employeeId, firstDayOfNextMonth, lastDayOfPreviousMonth);

        return sickLeaves;
    }

    @Override
    public List<SickLeave> getEmployeeSickLeavesUpToMonthInYear(int employeeId, int month, int year){
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfNextMonth = yearMonth.plusMonths(1).atDay(1);
        LocalDate lastDayOfPreviousYear = LocalDate.of(year,1,1).minusDays(1);

        List<SickLeave> sickLeaves = sickLeaveRepository.findAllByEmployeeIdAndStartDateBeforeAndEndDateAfter(employeeId, firstDayOfNextMonth, lastDayOfPreviousYear);

        return sickLeaves;
    }

    public List<SickLeave> getEmployeeSickLeavesInYear(int employeeId, int year){

        LocalDate firstDayOfNextYear = LocalDate.of(year,1,1).plusYears(1);
        LocalDate lastDayOfPreviousYear = LocalDate.of(year,1,1).minusDays(1);

        List<SickLeave> sickLeaves = sickLeaveRepository.findAllByEmployeeIdAndStartDateBeforeAndEndDateAfter(employeeId, firstDayOfNextYear, lastDayOfPreviousYear);

        return sickLeaves;
    }



    @Override
    public List<SickLeave> getEmployeesSickLeaves(int employeeId) {
        return sickLeaveRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    public void saveSickLeave(SickLeave sickLeave, int employeeId) throws SickLeavesOverlapException {
        sickLeave.validateAndSwapIfNeeded();
        List<SickLeave> employeeSickLeaves = sickLeaveRepository.findAllByEmployeeId(employeeId);
        for (SickLeave sl : employeeSickLeaves) {
            if (areSickLeavesOverlapped(sl, sickLeave)) {
                throw new SickLeavesOverlapException("Zwolnienie nakłada się na już istniejące!");
            }
        }
        employeeService.getEmployee(employeeId).addSickLeave(sickLeave);
        sickLeaveRepository.save(sickLeave);
    }

    @Override
    public SickLeave getSickLeave(int sickLeaveId) throws SickLeaveNotFoundException {
        return sickLeaveRepository.findById(sickLeaveId).orElseThrow(() -> new SickLeaveNotFoundException("Nie znaleziono zwolnienia o id: " + sickLeaveId));
    }

    @Override
    public void delete(int sickLeaveId) throws SickLeaveNotFoundException {
        sickLeaveRepository.delete(sickLeaveRepository.findById(sickLeaveId).orElseThrow(() -> new SickLeaveNotFoundException("Nie znaleziono zwolnienia o id: " + sickLeaveId)));
    }

    public int getNumberOfAbsenceDays(int employeeId, int month, int year) {
        int absenceDays = 0;
        List<SickLeave> sickLeaveList = getEmployeeSickLeavesMonthYear(employeeId, month, year);

        for (SickLeave sl : sickLeaveList) {
            absenceDays += sl.getNumberOfSickLeaveDaysInMonthYear(month, year);
        }
        return absenceDays;
    }

    private boolean areSickLeavesOverlapped(SickLeave sl1, SickLeave sl2) {
        return MyDateUtils.isOverlapped(sl1.getStartDate(), sl1.getEndDate(), sl2.getStartDate(), sl2.getEndDate());
    }


}
