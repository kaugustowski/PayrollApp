package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Salary;
import pl.wizyg.payroll.entity.SickLeave;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

  Salary findByEmployee_IdAndMonthAndYear(Integer employee_id, int month, int year);

    Salary findByEmployeeIdAndMonthAndYear(Integer employee_id, int month, int year);


    List<Salary> findAllByMonthAndYear(int month, int year);

}