package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wizyg.payroll.entity.Teacher;
import pl.wizyg.payroll.service.TeacherService;

import java.util.List;


@Controller
@RequestMapping("/teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;


    @GetMapping("/list")
    public String listTeachers(Model theModel) {

        List<Teacher> theTeachers = teacherService.getTeachers();

        theModel.addAttribute("teachers", theTeachers);

        return "list-teachers";
    }

    @GetMapping("/addTeacher")
    public String addEmployee(Model theModel) {

        return "teacher-form";

    }

    @PostMapping("/saveTeacher")
    public String saveCustomer(@ModelAttribute("teacher") Teacher teacher) {

        // save the customer using our service
        teacherService.saveTeacher(teacher);

        return "redirect:/teacher/list";
    }
}

