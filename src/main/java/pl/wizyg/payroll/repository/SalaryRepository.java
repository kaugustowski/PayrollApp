package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.DTO.SalaryListDTO;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Salary;
import pl.wizyg.payroll.entity.SickLeave;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

  Optional<Salary> findFirstByMonthAndYear(int month, int year);

  Salary findByEmployee_IdAndMonthAndYear(Integer employee_id, int month, int year);

  Salary findByEmployeeIdAndMonthAndYear(Integer employee_id, int month, int year);

  List<Salary> findAllByMonthAndYear(int month, int year);

  List<Salary> findAllByMonthAndYearAndEmployee_ActiveTrue(int month, int year);

  List<Salary> findAllByEmployeeIdAndMonthIsLessThanAndYearOrMonthGreaterThanEqualAndYear(int employeeId, int month, int thisYear, int sameMonth, int prevYear);

  List<Salary> findByEmployee_Id(int employeeId);

  @Query("select distinct new pl.wizyg.payroll.DTO.SalaryListDTO(s.month,s.year)  from Salary s order by s.year desc,s.month desc ")
  List<SalaryListDTO> getAllPayrollMonths();

  @Query("select distinct new pl.wizyg.payroll.DTO.SalaryListDTO(s.month,s.year)  from OvertimeSalary s order by s.year desc,s.month desc ")
  List<SalaryListDTO> getAllOVertimePayrollMonths();

  @Query("select distinct new pl.wizyg.payroll.DTO.SalaryListDTO(s.month,s.year)  from EssentialSalary s order by s.year desc,s.month desc ")
  List<SalaryListDTO> getAllEssentialPayrollMonths();
}
