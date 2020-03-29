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

        Session currentSession = sessionFactory.openSession();
        System.out.println("inside getTeachers after getCurrentSession");
        Query<Teacher> theQuery =
                currentSession.createQuery("from Teacher order by lastName asc",
                        Teacher.class);

        List<Teacher> teachers = theQuery.getResultList();
        System.out.println("inside getTeachers after getResultList");
//        currentSession.close();

        return teachers;
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(teacher);

//        currentSession.close();
    }

    @Override
    public Teacher getTeacher(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Teacher teacher = currentSession.get(Teacher.class, id);

//        currentSession.close();

        return teacher;
    }


    @Override
    public void deleteTeacher(int theId) {


        Session currentSession = sessionFactory.openSession();
//        currentSession.beginTransaction();


        Query theQuery;
        theQuery = currentSession.createQuery("delete from Teacher teacher where teacher.id=:teacherId");
        theQuery.setParameter("teacherId", theId);

        theQuery.executeUpdate();
//        currentSession.getTransaction().commit();
//        currentSession.close();
    }
}
