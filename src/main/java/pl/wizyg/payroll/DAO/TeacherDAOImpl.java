package pl.wizyg.payroll.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.entity.Salary;
import pl.wizyg.payroll.entity.SickLeave;
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
    public List<SickLeave> getSickLeaves(Integer employeeId) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<SickLeave> query = currentSession.createQuery("from SickLeave s where s.employee_id = :employeeId", SickLeave.class)
                .setParameter("employeeId", employeeId);

        List<SickLeave> sickLeaveList = query.getResultList();

        return sickLeaveList;
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
    public Teacher getTeacher(Integer id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Teacher teacher = currentSession.get(Teacher.class, id);
        System.out.println(teacher);

        return teacher;
    }


    @Override
    public void deleteTeacher(Integer theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery;
        theQuery = currentSession.createQuery("delete from Teacher where id=:teacherId");
        theQuery.setParameter("teacherId", theId);
        theQuery.executeUpdate();
    }

    @Override
    public void addTeachersSickLeave(Integer teacherId, SickLeave sickLeave) {
        Session currentSession = sessionFactory.getCurrentSession();

        Teacher teacher = getTeacher(teacherId);
        System.out.println("Got teacher with teacherId=" + teacher.getId());

        System.out.println(sickLeave);
        teacher.addSickLeave(sickLeave);

        currentSession.saveOrUpdate(sickLeave);
    }


    @Override
    public void addTeachersOvertime(Integer teacherId, Overtime overtime) {
        Session currentSession = sessionFactory.getCurrentSession();

        Teacher teacher = getTeacher(teacherId);
        System.out.println("Got teacher with teacherId=" + teacher.getId());

        System.out.println(overtime);
        //teacher.addOvertime(overtime);

        currentSession.saveOrUpdate(overtime);
    }

    @Override
    public void addTeachersSalary(Integer teacherId, Salary salary) {

    }
}
