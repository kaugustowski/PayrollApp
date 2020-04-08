package pl.wizyg.payroll.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.DAO.TeacherDAO;
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
    public List<Teacher> getTeachers() {
        return teacherDAO.getTeachers();
    }

    @Override
    public void saveTeacher(Teacher teacher) {

        teacherDAO.saveTeacher(teacher);
    }

    @Override
    public Teacher getTeacher(int id) {
        return teacherDAO.getTeacher(id);
    }

    @Override
    public void deleteTeacher(int id) {
        teacherDAO.deleteTeacher(id);
    }
}
