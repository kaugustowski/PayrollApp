package pl.wizyg.payroll.DAO;

import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.entity.Salary;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

public interface TeacherDAO {

    List<SickLeave> getSickLeaves(Integer teacherId);

    List<Teacher> getTeachers();

    void saveTeacher(Teacher teacher);

    Teacher getTeacher(Integer id);

    void deleteTeacher(Integer id);

    void addTeachersSickLeave(Integer teacherId, SickLeave sickLeave);

    void addTeachersOvertime(Integer teacherId, Overtime overtime);

    void addTeachersSalary(Integer teacherId, Salary salary);
}
