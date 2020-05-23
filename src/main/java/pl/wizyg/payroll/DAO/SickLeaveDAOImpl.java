//package pl.wizyg.payroll.DAO;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import pl.wizyg.payroll.entity.Employee;
//import pl.wizyg.payroll.entity.SickLeave;
//
//@Repository
//public class SickLeaveDAOImpl implements  SickLeaveDAO{
//
//    @Autowired
//    SessionFactory sessionFactory;
//
//    @Autowired
//    TeacherDAO teacherDAO;
//
//
//    @Override
//    public void addSickLeave(Integer employeeId, SickLeave sickLeave) {
//
//        Session currentSession = sessionFactory.getCurrentSession();
//
//        Employee employee = teacherDAO.getTeacher(employeeId);
//        System.out.println("Got employee with employeeId=" +employee.getId());
//
//        System.out.println(sickLeave);
//        employee.addSickLeave(sickLeave);
//
//        currentSession.saveOrUpdate(sickLeave);
//    }
//}
