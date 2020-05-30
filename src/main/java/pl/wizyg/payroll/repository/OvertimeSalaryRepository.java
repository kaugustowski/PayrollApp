package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wizyg.payroll.entity.OvertimeSalary;
import pl.wizyg.payroll.entity.Salary;

import java.util.List;

public interface OvertimeSalaryRepository extends JpaRepository<OvertimeSalary, Integer> {
    List<OvertimeSalary> findAllByMonthAndYear(int month, int year);

}
