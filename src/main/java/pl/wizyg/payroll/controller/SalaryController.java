package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.wizyg.payroll.entity.Salary;
import pl.wizyg.payroll.service.SalaryService;

import java.util.List;

@Controller
@RequestMapping("/salary")
public class SalaryController {
    final
    SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }
    @RequestMapping("list/{year}/{month}")
    public String listSalaries(Model model, @PathVariable int month,@PathVariable int year ){

        List<Salary> salaries = salaryService.getEssentialSalariesInMonthYear(month,year);

        model.addAttribute("salaries", salaries);

        return "salary-list";
    }

    @RequestMapping("/details/{year}/{month}")
    public String showSalaryDetails(Model model, @PathVariable int month, @PathVariable int year, @RequestParam int employeeId){
        Salary salary = salaryService.calculateSalary(employeeId, month, year);


        model.addAttribute("salary", salary);

        salaryService.saveSalary(salary);

        return "salary-details";

    }
}
