package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.Employee;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.entity.Teacher;
import pl.wizyg.payroll.repository.EmployeeRepository;
import pl.wizyg.payroll.repository.SickLeaveRepository;
import pl.wizyg.payroll.service.TeacherService;

import java.util.List;
import java.util.Optional;

//import pl.wizyg.payroll.repository.SickLeaveRepository;

@Controller
@RequestMapping("/sickLeave")
public class SickLeaveController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    SickLeaveRepository sickLeaveRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/list/{employeeId}")
    public String showSickLeaves(@PathVariable int employeeId, Model theModel) {

        Teacher teacher = teacherService.getTeacher(employeeId);

        List<SickLeave> sickLeaveList = sickLeaveRepository.findAllByEmployeeId(employeeId);

        theModel.addAttribute("teacher", teacher);
        theModel.addAttribute("sickLeaves", sickLeaveList);

        return "sick-leaves";
    }

    @GetMapping("/add/{employeeId}")
    public String showSickLeaveForm(@PathVariable int employeeId, Model theModel) {

        Employee theTeacher = employeeRepository.findById(employeeId).get();

        SickLeave sickLeave = new SickLeave();


        theModel.addAttribute("teacher", theTeacher);
        theModel.addAttribute("sickLeave", sickLeave);

        System.out.println("FORM fsdfzs");

        return "sickleave-form2";
    }

    @PostMapping("/save/{employeeId}")
    public String saveSickLeave(@ModelAttribute("sickLeave") SickLeave sickLeave, @PathVariable int employeeId) {
        System.out.println("Saving nr 1!!!!!  From:" + sickLeave.getStartDate() + "  to: " + sickLeave.getEndDate());


        teacherService.saveTeachersSickLeave(employeeId, sickLeave);

        System.out.println("Saving!!!!!  From:" + sickLeave.getStartDate() + "  to: " + sickLeave.getEndDate());
        return "redirect:/teacher/sickLeaves/{employeeId}";
    }
}
