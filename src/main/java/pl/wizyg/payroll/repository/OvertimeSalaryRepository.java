package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.OvertimeSalary;

import java.util.List;

@Repository
public interface OvertimeSalaryRepository extends JpaRepository<OvertimeSalary, Integer> {

    List<OvertimeSalary> findAllByMonthAndYearOrderByEmployee_LastName(int month, int year);

    OvertimeSalary findByEmployee_IdAndMonthAndYear(int employeeId, int month, int year);

}
