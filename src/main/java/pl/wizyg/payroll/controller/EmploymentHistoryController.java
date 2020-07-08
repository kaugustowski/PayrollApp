package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.EmploymentHistory;
import pl.wizyg.payroll.service.EmployeeService;
import pl.wizyg.payroll.service.EmploymentHistoryService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/history")
@Controller
public class EmploymentHistoryController {

    @Autowired
    EmploymentHistoryService employmentHistoryService;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/list/{employeeId}")
    public String historyList(@PathVariable int employeeId, Model model){
        List<EmploymentHistory> employeeEmploymentHistory=
        employmentHistoryService.getEmploymentHistoryListForEmployee(employeeId);
        Employee employee = employeeService.getEmployee(employeeId);
        model.addAttribute("history",employeeEmploymentHistory);
        model.addAttribute("employee", employee);

        return "history-list";
    }

    @RequestMapping("/add/{employeeId}")
    public String showEmploymentHistoryForm(@PathVariable int employeeId, Model model){
        Employee employee = employeeService.getEmployee(employeeId);
        EmploymentHistory employmentHistory = new EmploymentHistory();
        model.addAttribute("empHistory", employmentHistory);
        model.addAttribute("employee", employee);

        return "emp-history-form";
    }

    @RequestMapping("/delete/{employeeId}")
    public String showEmploymentHistoryForm(@PathVariable int employeeId, @RequestParam int empHistId){

        employmentHistoryService.deleteEmploymentHistory(empHistId);

        return "redirect:/history/{employeeId}";
    }

    @PostMapping("/save/{employeeId}")
    public String saveEmploymentHistory(@Valid @ModelAttribute("empHistory") EmploymentHistory employmentHistory, BindingResult bindingResult, @PathVariable int employeeId) {

        if (bindingResult.hasErrors())
            return "emp-history-form";

        employeeService.getEmployee(employeeId).addEmploymentHistory(employmentHistory);
        employmentHistoryService.saveEmploymentHistory(employmentHistory, employeeId);

        return "redirect:/history/list/{employeeId}";
    }

}
