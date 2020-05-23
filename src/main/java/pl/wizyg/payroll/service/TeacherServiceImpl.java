package pl.wizyg.payroll.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.DAO.TeacherDAO;
import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDAO teacherDAO;


    public TeacherServiceImpl(@Autowired TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<SickLeave> getSickLeaves(Integer teacherId) {
        return teacherDAO.getSickLeaves(teacherId);
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherDAO.getTeachers();
    }

    @Override
    public void saveTeacher(Teacher teacher) {

        teacherDAO.saveTeacher(teacher);
    }

    @Override
    public Teacher getTeacher(Integer id) {
        return teacherDAO.getTeacher(id);
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherDAO.deleteTeacher(id);
    }

    @Override
    public void saveTeachersSickLeave(Integer teacherId, SickLeave sickLeave) {
        teacherDAO.addTeachersSickLeave(teacherId, sickLeave);
    }

    @Override
    public void saveTeachersOvertime(Integer teacherId, Overtime overtime) {
        teacherDAO.addTeachersOvertime(teacherId, overtime);
    }


}
