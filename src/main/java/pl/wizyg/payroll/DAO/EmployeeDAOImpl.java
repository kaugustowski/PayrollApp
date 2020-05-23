package pl.wizyg.payroll.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Employee> getEmployees() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Teacher> teacherQuery = currentSession.createQuery(" from Employee ", Teacher.class);

        List<Teacher> teachers = teacherQuery.getResultList();


        List<Employee> employees = new ArrayList<>();

        employees.addAll(teachers);


        return employees;
    }

    @Override
    public void saveEmployee() {

    }

    @Override
    public Employee getEmployee(int id) {
        return null;
    }

    @Override
    public void deleteEmployee(int id) {

    }
}
