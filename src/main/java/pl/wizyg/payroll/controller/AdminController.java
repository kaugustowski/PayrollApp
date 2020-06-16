package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wizyg.payroll.DTO.UserDTO;
import pl.wizyg.payroll.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/employees")
    public String listEmployees(){


        return "admin-emp-list";
    }

    @RequestMapping("/users")
    public String listUsers(){

        return "admin-users-list";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        UserDTO user =
        new UserDTO();
        model.addAttribute("user", user);

        return "register-form";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") UserDTO userDTO ){

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = new User(userDTO.getUsername(), encodedPassword, authorities);
        jdbcUserDetailsManager.createUser(user);

        return "register-form";
    }
}
