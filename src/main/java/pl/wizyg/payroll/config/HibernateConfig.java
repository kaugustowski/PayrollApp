package pl.wizyg.payroll.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import pl.wizyg.payroll.entity.AdministrativeEmployee;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Teacher;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    private static SessionFactory sessionFactory;

    @Bean(name = "sessionFactory")
    public static SessionFactory sessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();


                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                settings.put(Environment.URL, "jdbc:sqlserver://localhost;database=payroll");
                settings.put(Environment.USER, "wizyg");
                settings.put(Environment.PASS, "wizyg");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
//                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Teacher.class);
                configuration.addAnnotatedClass(AdministrativeEmployee.class);
                configuration.addAnnotatedClass(Employee.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}

