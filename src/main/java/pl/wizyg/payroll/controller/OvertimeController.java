package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.repository.EmployeeRepository;

import java.util.Optional;

@Controller
@RequestMapping("/overtime")
public class OvertimeController {

    final
    EmployeeRepository employeeRepository;

    public OvertimeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/add/{employeeId}")
    public String showOvertimeForm(@PathVariable int employeeId, Model theModel){
        Optional<Employee> theTeacher = employeeRepository.findById(employeeId);

        SickLeave sickLeave = new SickLeave();


        theModel.addAttribute("teacher", theTeacher);
        theModel.addAttribute("sickLeave", sickLeave);

        System.out.println("FORM fsdfzs");

        return "overtime-form";
    }

}
