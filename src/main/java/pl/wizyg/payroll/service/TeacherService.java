package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<SickLeave> getSickLeaves(Integer teacherId);

    List<Teacher> getTeachers();

    void saveTeacher(Teacher teacher);

    Teacher getTeacher(Integer id);

    void deleteTeacher(Integer id);

    void saveTeachersSickLeave(Integer teacherId, SickLeave sickLeave);

    void saveTeachersOvertime(Integer teacherId, Overtime overtime);

}
