package pl.wizyg.payroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.service.EmployeeService;
import pl.wizyg.payroll.service.SickLeaveService;

import java.util.List;

@Controller
@RequestMapping("/sickLeave")
public class SickLeaveController {

    final
    EmployeeService employeeService;
    final
    SickLeaveService sickLeaveService;

    public SickLeaveController(EmployeeService employeeService, SickLeaveService sickLeaveService) {
        this.employeeService = employeeService;
        this.sickLeaveService = sickLeaveService;
    }

    @GetMapping("/list/{employeeId}")
    public String showSickLeaves(@PathVariable int employeeId, Model theModel) {

        Employee employee = employeeService.getEmployee(employeeId);

        List<SickLeave> sickLeaveList = sickLeaveService.getEmployeesSickLeaves(employeeId);

        theModel.addAttribute("employee", employee);
        theModel.addAttribute("sickLeaves", sickLeaveList);

        return "sick-leaves";
    }

    @GetMapping("/add/{employeeId}")
    public String showSickLeaveForm(@PathVariable int employeeId, Model theModel) {

        Employee employee = employeeService.getEmployee(employeeId);

        SickLeave sickLeave = new SickLeave();

        theModel.addAttribute("employee", employee);
        theModel.addAttribute("sickLeave", sickLeave);

        return "sickleave-form";
    }

    @PostMapping("/save/{employeeId}")
    public String saveSickLeave(@ModelAttribute("sickLeave") SickLeave sickLeave, @PathVariable int employeeId) {

        sickLeaveService.saveSickLeave(sickLeave);

        return "redirect:/sickLeaves/list/{employeeId}";
    }
}