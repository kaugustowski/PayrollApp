package pl.wizyg.payroll.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.entity.Test;

@Transactional
@Repository
public class TestDAO {


    private final SessionFactory sessionFactory;

    @Autowired
    public TestDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void saveTest(Test test) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(test);

    }

}
