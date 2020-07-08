package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.SickLeave;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SickLeaveRepository extends JpaRepository<SickLeave, Integer> {

    List<SickLeave> findAllByEmployeeIdOrderByStartDateDesc(int employeeId);

    List<SickLeave> findAllByEmployeeIdAndStartDateBeforeAndEndDateAfter(
            int employeeId,
            LocalDate firstDayOfNextMonth,
            LocalDate lastDayOfPreviousMonth);
}
