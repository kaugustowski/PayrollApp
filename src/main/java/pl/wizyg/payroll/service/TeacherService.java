package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getTeachers();

    void saveTeacher(Teacher teacher);

    Teacher getTeacher(int id);

    void deleteTeacher(int id);
}
