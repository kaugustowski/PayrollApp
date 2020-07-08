package pl.wizyg.payroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.exception.SickLeaveNotFoundException;
import pl.wizyg.payroll.exception.SickLeavesOverlapException;
import pl.wizyg.payroll.service.EmployeeService;
import pl.wizyg.payroll.service.SickLeaveService;

import javax.validation.Valid;
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

        SickLeave sickLeave = new SickLeave(employee);

        theModel.addAttribute("employee", employee);
        theModel.addAttribute("sickLeave", sickLeave);

        return "sickleave-form";
    }

    @GetMapping("/edit/{employeeId}")
    public String updateSickLeave(@RequestParam int sickLeaveId, @PathVariable int employeeId, Model theModel) throws SickLeaveNotFoundException {

        Employee employee = employeeService.getEmployee(employeeId);

        SickLeave sickLeave = sickLeaveService.getSickLeave(sickLeaveId);

        theModel.addAttribute("employee", employee);
        theModel.addAttribute("sickLeave", sickLeave);

        return "sickleave-form";
    }

    @PostMapping("/save/{employeeId}")
    public String saveSickLeave(@Valid @ModelAttribute("sickLeave") SickLeave sickLeave, BindingResult bindingResult, @PathVariable int employeeId) throws SickLeavesOverlapException {

        if (bindingResult.hasErrors()) {
            return "sickleave-form";
        }

        sickLeaveService.saveSickLeave(sickLeave, employeeId);

        return "redirect:/sickLeave/list/{employeeId}";
    }

    @GetMapping("/delete/{employeeId}")
    public String deleteSickLeave(@RequestParam int sickLeaveId, @PathVariable int employeeId) throws SickLeaveNotFoundException {

        sickLeaveService.delete(sickLeaveId);

        return "redirect:/sickLeave/list/{employeeId}";
    }


}