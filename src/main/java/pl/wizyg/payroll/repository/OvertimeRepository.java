package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.Overtime;

import java.util.List;

@Repository
public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

    List<Overtime> findAllByEmployee_Id(int employeeId);

    Overtime findByEmployee_IdAndMonthAndYear(Integer employee_id, int month, int year);
}
