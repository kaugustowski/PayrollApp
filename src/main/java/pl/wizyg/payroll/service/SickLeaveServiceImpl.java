package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.repository.SickLeaveRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class SickLeaveServiceImpl implements SickLeaveService {

    SickLeaveRepository sickLeaveRepository;

    @Autowired
    public SickLeaveServiceImpl(SickLeaveRepository sickLeaveRepository) {
        this.sickLeaveRepository = sickLeaveRepository;
    }

    @Override
    public List<SickLeave> getTeachersSickLeavesMonthYear(int teacherId, int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfNextMonth = yearMonth.plusMonths(1).atDay(1);
        LocalDate lastDayOfPreviousMonth = yearMonth.minusMonths(1).atEndOfMonth();

        List<SickLeave> sickLeaves = sickLeaveRepository.findAllByTeacherIdAndStartDateBeforeAndEndDateAfter(teacherId, firstDayOfNextMonth, lastDayOfPreviousMonth);

        return sickLeaves;
    }

    public int getNumberOfAbsenceDays(List<SickLeave> sickLeaves, int month, int year) {
        int absenceDays = 0;

        for (SickLeave sl : sickLeaves) {
            absenceDays += sl.getNumberOfSickLeaveDaysInMonthYear(month, year);
        }


        return absenceDays;
    }


}
