package pl.wizyg.payroll.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.entity.Teacher;
import pl.wizyg.payroll.repository.TeacherRepository;

import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    final
    TeacherRepository teacherRepository;


    public TeacherServiceImpl(@Autowired TeacherRepository teacherRepository) {

        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getTeachers() {
        return teacherRepository.findAllByOrderByLastName();
    }

    @Override
    public List<Teacher> getActiveTeachers() {
        return teacherRepository.findAllByActiveTrueOrderByLastName();
    }

    @Override
    public List<Teacher> getInactiveTeachers() {
        return teacherRepository.findAllByActiveFalseOrderByLastName();
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacher(Integer id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public void deleteTeacher(Integer id) {
        teacherRepository.delete(getTeacher(id));
    }

    @Override
    public void setInactiveTeacher(Integer id) {
        Teacher teacher = teacherRepository.findById(id).get();
        teacher.setActive(false);
        teacherRepository.save(teacher);
    }

    @Override
    public void setActiveTeacher(Integer id) {
        Teacher teacher = teacherRepository.findById(id).get();
        teacher.setActive(true);
        teacherRepository.save(teacher);
    }


}
