package pl.wizyg.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Teacher;
import pl.wizyg.payroll.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getActiveEmployees() {
        return employeeRepository.findAllByActiveTrue();
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).get();
    }


    @Override
    public void createEmployeeAccountIfDoesNotExist(Employee employee){
        if(!employeeRepository.findById(employee.getId()).isPresent()){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        String username = employee.getEmail();
        String encodedPassword = passwordEncoder.encode(employee.getPesel());

        User user = new User(username, encodedPassword, authorities);
        jdbcUserDetailsManager.createUser(user);
        }
    }
}
