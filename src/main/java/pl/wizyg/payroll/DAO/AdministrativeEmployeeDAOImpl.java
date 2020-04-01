package pl.wizyg.payroll.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.AdministrativeEmployee;

import java.util.List;

@Repository
public class AdministrativeEmployeeDAOImpl implements AdministrativeEmployeeDAO {

    private final SessionFactory sessionFactory;

    public AdministrativeEmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<AdministrativeEmployee> getAdministrativeEmployees() {

        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();
        System.out.println("inside getTeachers after getCurrentSession");
        Query<AdministrativeEmployee> theQuery =
                currentSession.createQuery("from AdministrativeEmployee order by lastName asc",
                        AdministrativeEmployee.class);

        List<AdministrativeEmployee> administrativeEmployees = theQuery.getResultList();
        System.out.println("inside getTeachers after getResultList");
        currentSession.getTransaction().commit();
        currentSession.close();

        return administrativeEmployees;
    }

    @Override
    public void saveAdministrativeEmployee(AdministrativeEmployee employee) {
        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();
        currentSession.saveOrUpdate(employee);
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    @Override
    public AdministrativeEmployee getAdministrativeEmployee(int id) {

        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();

        AdministrativeEmployee employee = currentSession.get(AdministrativeEmployee.class, id);

        currentSession.getTransaction().commit();
        currentSession.close();

        return employee;
    }

    @Override
    public void deleteAdministrativeEmployee(int id) {
        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();

        Query theQuery;
        theQuery = currentSession.createQuery("delete from AdministrativeEmployee where id=:administrativeEmployeeId");
        theQuery.setParameter("administrativeEmployeeId", id);

        theQuery.executeUpdate();
        currentSession.getTransaction().commit();
        currentSession.close();
    }
}
