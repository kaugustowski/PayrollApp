package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.SickLeave;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SickLeaveRepository extends JpaRepository<SickLeave, Integer> {

    List<SickLeave> findAllByTeacherId(int teacherId);

    List<SickLeave> findAllByTeacherIdAndStartDateBeforeAndEndDateAfter(int teacherId, LocalDate firstDayOfNextMonth, LocalDate lastDayOfPreviousMonth);
}
