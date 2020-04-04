package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

        Teacher teacher = new Teacher();

        theModel.addAttribute("teacher", teacher);


        return "teacher-form";

    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {

        // save the customer using our service
        teacherService.saveTeacher(teacher);

        return "redirect:/teacher/addTeacher";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("teacherId") int theId,
                                    Model theModel) {

        Teacher theTeacher = teacherService.getTeacher(theId);

        theModel.addAttribute("teacher", theTeacher);

        return "teacher-form";
    }

    @GetMapping("/delete")
    public String deleteTeacher(@RequestParam("teacherId") int theId) {

        // delete the customer
        teacherService.deleteTeacher(theId);

        return "redirect:/teacher/list";
    }

    @PostMapping("/calculate")
    public String calculate() {

        List<Teacher> teachers = teacherService.getTeachers();
        teachers.forEach(Teacher::calculateSalary);

        return "redirect:/teacher/list";
    }

}


