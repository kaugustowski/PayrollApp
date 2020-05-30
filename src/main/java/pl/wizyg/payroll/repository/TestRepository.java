package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.entity.Test;

@Transactional
public interface TestRepository extends JpaRepository <Test, Integer> {
}
