package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wizyg.payroll.entity.Overtime;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

}
