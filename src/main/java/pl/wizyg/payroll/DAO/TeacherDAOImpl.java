package pl.wizyg.payroll.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.Teacher;

import java.util.List;

@Repository
public class TeacherDAOImpl implements TeacherDAO {


    private final SessionFactory sessionFactory;

    @Autowired
    public TeacherDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Teacher> getTeachers() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Teacher> theQuery =
                currentSession.createQuery("from Teacher order by lastName asc",
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
    public void deleteTeacher(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery;
        theQuery = currentSession.createQuery("delete from Teacher where id=:teacherId");
        theQuery.setParameter("teacherId", theId);
        theQuery.executeUpdate();
    }
}
