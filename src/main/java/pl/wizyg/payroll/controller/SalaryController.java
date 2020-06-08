package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.EssentialSalary;
import pl.wizyg.payroll.entity.OvertimeSalary;
import pl.wizyg.payroll.entity.Salary;
import pl.wizyg.payroll.service.EmployeeService;
import pl.wizyg.payroll.service.SalaryService;

import java.util.List;

@Controller
@RequestMapping("/salary")
public class SalaryController {

    final
    SalaryService salaryService;

    @Autowired
    EmployeeService employeeService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }
    @RequestMapping("list/{year}/{month}")
    public String listSalaries(Model model, @PathVariable int month,@PathVariable int year ){

        List<Salary> salaries = salaryService.getSalariesForActiveEmployeesInMonthYear(month,year);

        model.addAttribute("salaries", salaries);

        return "salary-list";
    }

    @RequestMapping("/overtimeList/{year}/{month}")
    public String listOvertimeSalaries(Model model, @PathVariable int month,@PathVariable int year ){

        List<OvertimeSalary> salaries = salaryService.getOvertimeSalariesInMonthYear(month,year);

        model.addAttribute("overtimeSalaries", salaries);

        return "salary-list";
    }

    @RequestMapping("/details/{year}/{month}")
    public String showSalaryDetails(Model model, @PathVariable int month, @PathVariable int year, @RequestParam int employeeId){
        Salary salary = salaryService.calculateSalary(employeeId, month, year);


        model.addAttribute("salary", salary);

        salaryService.saveSalary(salary, employeeId );

        return "salary-details";

    }

    @PostMapping("/saveSalary/{employeeId}")
    public String showSalaryDetails(@ModelAttribute("salary") EssentialSalary salary, @PathVariable int employeeId){

        employeeService.getEmployee(employeeId).addSalary(salary);
        salaryService.saveSalary(salary, employeeId );

        return "redirect:salary/list/2020/5";
    }

    @PostMapping("/calculateSalaries/{year}/{month}")
    public String calculateSalaries(@PathVariable int year, @PathVariable int month){

        salaryService.saveSalaries(salaryService.calculateSalariesForActiveEmployees(month, year));

        return "redirect:/salary/list/{year}/{month}";
    }

    @PostMapping("/calculateOvertimeSalaries/{year}/{month}")
    public String calculateOvertimeSalaries(@PathVariable int year, @PathVariable int month){


        salaryService.saveSalaries(salaryService.calculateOvertimeSalaryForActiveEmployees(month, year));

        return "redirect:/salary/list/{year}/{month}";
    }
}
