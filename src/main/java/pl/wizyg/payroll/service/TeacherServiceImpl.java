package pl.wizyg.payroll.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.DAO.TeacherDAO;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDAO teacherDAO;

    public TeacherServiceImpl(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    @Transactional
    public List<Teacher> getTeachers() {
        return teacherDAO.getTeachers();
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {

        teacherDAO.saveTeacher(teacher);
    }

    @Override
    @Transactional
    public Teacher getTeacher(int id) {
        return null;
    }

    @Override
    @Transactional
    public void deleteTeacher(int id) {

    }
}
