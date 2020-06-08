package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Teacher;
import pl.wizyg.payroll.repository.EmployeeRepository;
import pl.wizyg.payroll.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    final
    EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @Qualifier("employee")

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> theEmployees = employeeRepository.findAll();

        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }
    @RequestMapping("/{employeeId}")
    public String details(Model model, @PathVariable int employeeId){

        Employee teacher = employeeService.getEmployee(employeeId);

        model.addAttribute("teacher", teacher);

        return "employee-form";
    }
}
