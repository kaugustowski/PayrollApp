package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.EssentialSalary;

import java.util.List;

@Repository
public interface EssentialSalaryRepository extends JpaRepository<EssentialSalary, Integer> {

    EssentialSalary findByEmployee_IdAndMonthAndYear(Integer employee_id, int month, int year);

    List<EssentialSalary> findAllByMonthAndYear(int month, int year);

    List<EssentialSalary> findAllByMonthAndYearAndEmployee_ActiveTrue(int month, int year);

    List<EssentialSalary> findAllByEmployeeIdAndMonthIsLessThanAndYearOrMonthGreaterThanEqualAndYear(int employeeId, int month, int thisYear, int sameMonth, int prevYear);

    List<EssentialSalary> findByEmployee_Id(int employeeId);
}
