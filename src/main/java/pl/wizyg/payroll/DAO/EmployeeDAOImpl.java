//package pl.wizyg.payroll.DAO;
//
//import java.util.List;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import pl.wizyg.payroll.entity.Employee;
//
//import pl.wizyg.payroll.entity.Teacher;
//
//public class EmployeeDAOImpl implements EmployeeDAO {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//
//    @Override
//    public List<? extends Employee> getEmployees() {
//
//        Session currentSession = sessionFactory.getCurrentSession();
//
//        Query<Teacher> teacherQuery = currentSession.createSQLQuery("select from teacher ")
//
//        List<Teacher> teachers = teacherQuery.getResultList();
//
//        return teachers;
//    }
//
//    @Override
//    public void saveEmployee() {
//
//    }
//
//    @Override
//    public Employee getEmployee(int id) {
//        return null;
//    }
//
//    @Override
//    public void deleteEmployee(int id) {
//
//    }
//}
