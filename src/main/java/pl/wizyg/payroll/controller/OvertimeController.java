package pl.wizyg.payroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.service.EmployeeService;

@Controller
@RequestMapping("/overtime")
public class OvertimeController {

    final
    EmployeeService employeeService;

    public OvertimeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add/{employeeId}")
    public String showOvertimeForm(@PathVariable int employeeId, Model theModel){

        Employee employee = employeeService.getEmployee(employeeId);

        Overtime overtime = new Overtime();

        theModel.addAttribute("employee", employee);
        theModel.addAttribute("overtime", overtime);

        return "overtime-form";
    }
}
