package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Qualifier("employee")

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> theEmployees = employeeService.getEmployees();

        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }

//    @GetMapping("/addEmployee")
//    public String addEmployee(Model theModel){
//
//
//
//
//
//        return "employee-form";
//
//    }

//    @PostMapping("/saveCustomer")
//    public String saveCustomer(@ModelAttribute("customer") Employee employee) {
//
//        // save the customer using our service
//        customerService.saveCustomer(theCustomer);
//
//        return "redirect:/customer/list";
//    }
}
