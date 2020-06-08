package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.EmploymentHistory;

import java.util.List;

@Repository
public interface EmploymentHistoryRepository extends JpaRepository<EmploymentHistory,Integer> {

    List<EmploymentHistory> findAllByEmployee_Id(int id);
}
