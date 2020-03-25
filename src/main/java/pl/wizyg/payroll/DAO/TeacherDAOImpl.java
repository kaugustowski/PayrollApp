package pl.wizyg.payroll.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

@Repository
public class TeacherDAOImpl implements TeacherDAO {

    @Autowired
    @Qualifier("mySessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<Teacher> getTeachers() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Teacher> theQuery =
                currentSession.createQuery("from Teacher",
                        Teacher.class);

        List<Teacher> teachers = theQuery.getResultList();

        return teachers;
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(teacher);
    }

    @Override
    public Teacher getTeacher(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Teacher teacher = currentSession.get(Teacher.class, id);

        return teacher;
    }

    @Override
    public void deleteTeacher(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from Teacher where id=:teacherId");
        theQuery.setParameter("teacherId", id);

        theQuery.executeUpdate();
    }
}