package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wizyg.payroll.DTO.SalaryListDTO;
import pl.wizyg.payroll.entity.*;
import pl.wizyg.payroll.service.EmployeeService;
import pl.wizyg.payroll.service.SalaryService;

import java.util.List;

@Controller
@RequestMapping("/salary")
public class SalaryController {

    final
    SalaryService salaryService;

    final
    EmployeeService employeeService;

    public SalaryController(SalaryService salaryService, EmployeeService employeeService) {
        this.salaryService = salaryService;
        this.employeeService = employeeService;
    }

    @RequestMapping("essentialList")
    public String listEssentialSalaries(Model model){
        List<SalaryListDTO> salaries = salaryService.getEssentialSalaryListDTO();
        model.addAttribute("salaryMonths", salaries);
        return "salaries";
    }

    @RequestMapping("overtimeList")
    public String listOvertimeSalaries(Model model){
        List<SalaryListDTO> salaries = salaryService.getOvertimeSalaryListDTO();
        model.addAttribute("salaryMonths", salaries);
        return "salaries";
    }

    @RequestMapping("list")
    public String listSalaries(Model model){
        List<SalaryListDTO> salaries = salaryService.getSalaryListDTO();
        model.addAttribute("salaryMonths", salaries);
        return "salaries";
    }

    @RequestMapping("list/{year}/{month}")
    public String listSalaries(Model model, @PathVariable int month,@PathVariable int year ){

        List<Salary> salaries = salaryService.getSalariesInMonthYear(month,year);

        model.addAttribute("salaries", salaries);

        return "salary-list";
    }

    @RequestMapping("list/active/{year}/{month}")
    public String listSalariesForActiveEmployees(Model model, @PathVariable int month,@PathVariable int year ){

        List<Salary> salaries = salaryService.getSalariesForActiveEmployeesInMonthYear(month,year);

        model.addAttribute("salaries", salaries);

        return "salary-list";
    }

    @RequestMapping("list/employee/{employeeId}")
    public String listSalaries(Model model, @PathVariable int employeeId){
        Employee employee = employeeService.getEmployee(employeeId);
        List<Salary> salaries = salaryService.getEmployeeSalaries(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("salaries", salaries);
        return "salary-list";
    }

    @RequestMapping("/overtimeList/{year}/{month}")
    public String listOvertimeSalaries(Model model, @PathVariable int month,@PathVariable int year ){
        List<OvertimeSalary> salaries = salaryService.getOvertimeSalariesInMonthYear(month,year);
        model.addAttribute("overtimeSalaries", salaries);
        return "salary-list";
    }

    @RequestMapping("/essentialList/{year}/{month}")
    public String listEssentialSalaries(Model model, @PathVariable int month,@PathVariable int year ){
        List<EssentialSalary> salaries = salaryService.getEssentialSalariesInMonthYear(month,year);
        model.addAttribute("overtimeSalaries", salaries);
        return "salary-list";
    }

    @RequestMapping("/calculateSalary/{year}/{month}")
    public String showSalaryDetails(@PathVariable int month, @PathVariable int year, @RequestParam int employeeId, RedirectAttributes redirectAttrs){
        Salary salary = salaryService.calculateSalary(employeeId, month, year);

        employeeService.getEmployee(employeeId).addSalary(salary);
        salaryService.saveSalary(salary, employeeId );
        System.out.println(salary.getYear() + salary.getMonth());

        redirectAttrs.addAttribute("year", salary.getYear());
        redirectAttrs.addAttribute("month", salary.getMonth());

        return "redirect:/salary/list/{year}/{month}" ;
    }

    @GetMapping("/saveSalary/{employeeId}")
    public String showSalaryDetails(@ModelAttribute("salary") EssentialSalary salary, @PathVariable int employeeId, RedirectAttributes redirectAttrs){

        salaryService.getEmployeeSalaries(employeeId);
        employeeService.getEmployee(employeeId).addSalary(salary);
        salaryService.saveSalary(salary, employeeId );
        System.out.println(salary.getYear() + salary.getMonth());

        redirectAttrs.addAttribute("year", salary.getYear());
        redirectAttrs.addAttribute("month", salary.getMonth());

        redirectAttrs.addAttribute("salary", salary);
        redirectAttrs.addAttribute("employeeId", employeeId);

        return "redirect:/salary/saveSalary/{employeeId}";

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
