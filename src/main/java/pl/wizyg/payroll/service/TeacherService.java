package pl.wizyg.payroll.service;

import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getTeachers();

    List<Teacher> getActiveTeachers();

    void saveTeacher(Teacher teacher);

    Teacher getTeacher(Integer id);

    void deleteTeacher(Integer id);

    void setInactiveTeacher(Integer id);


}
