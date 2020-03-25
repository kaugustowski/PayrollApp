package pl.wizyg.payroll.DAO;

import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

public interface TeacherDAO {
    List<Teacher> getTeachers();

    void saveTeacher(Teacher teacher);

    Teacher getTeacher(int id);

    void deleteTeacher(int id);
}
