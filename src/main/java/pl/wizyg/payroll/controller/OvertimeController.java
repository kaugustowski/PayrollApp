package pl.wizyg.payroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.Overtime;
import pl.wizyg.payroll.service.EmployeeService;
import pl.wizyg.payroll.service.OvertimeService;

import java.util.List;

@Controller
@RequestMapping("/overtime")
public class OvertimeController {

    final
    EmployeeService employeeService;
    final
    OvertimeService overtimeService;

    public OvertimeController(EmployeeService employeeService, OvertimeService overtimeService) {
        this.employeeService = employeeService;
        this.overtimeService = overtimeService;
    }

    @GetMapping("/list/{employeeId}")
    public String showOvertimeList(@PathVariable int employeeId, Model theModel) {

        Employee employee = employeeService.getEmployee(employeeId);

        List<Overtime> overtimes = overtimeService.getEmployeeOvertimeList(employeeId);

        theModel.addAttribute("employee", employee);
        theModel.addAttribute("overtimes", overtimes);

        return "overtime-list";
    }

    @GetMapping("/add/{employeeId}")
    public String showOvertimeForm(@PathVariable int employeeId, Model theModel) {

        Employee employee = employeeService.getEmployee(employeeId);

        Overtime overtime = new Overtime(employee);

        theModel.addAttribute("employee", employee);
        theModel.addAttribute("overtime", overtime);

        return "overtime-form";
    }

    @PostMapping("/save/{employeeId}")
    public String saveOvertime(@PathVariable int employeeId, @ModelAttribute Overtime overtime){

        overtimeService.saveOvertime(overtime, employeeId );

        return "redirect:/overtime/list/{employeeId}";

    }
}
