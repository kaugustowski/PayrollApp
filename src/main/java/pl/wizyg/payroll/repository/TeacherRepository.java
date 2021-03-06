package pl.wizyg.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    List<Teacher> findAllByActiveTrue();

    List<Teacher> findAllByActiveTrueOrderByLastName();

    List<Teacher> findAllByOrderByLastName();

    List<Teacher> findAllByActiveFalse();

    List<Teacher> findAllByActiveFalseOrderByLastName();

}
