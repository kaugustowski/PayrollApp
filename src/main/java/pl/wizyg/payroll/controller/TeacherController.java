package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.Education;
import pl.wizyg.payroll.entity.SickLeave;
import pl.wizyg.payroll.entity.Teacher;
import pl.wizyg.payroll.entity.TeacherType;
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
    public String addTeacher(Model theModel) {

        Teacher teacher = new Teacher();
        Education[] education = Education.values();
        TeacherType[] teacherTypes = TeacherType.values();

        theModel.addAttribute("teacher", teacher);
        theModel.addAttribute("educationValues", education);
        theModel.addAttribute("teacherTypeValues", teacherTypes);

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
        Education[] education = Education.values();

        theModel.addAttribute("teacher", theTeacher);
        theModel.addAttribute("educationValues", education);
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

    @GetMapping("/sickLeaves/{teacherId}")
    public String showSickLeaves(@PathVariable int teacherId, Model theModel) {

        Teacher teacher = teacherService.getTeacher(teacherId);

        List<SickLeave> sickLeaveList = teacherService.getTeacher(teacherId).getSickLeaves();

        theModel.addAttribute("teacher", teacher);
        theModel.addAttribute("sickLeaves", sickLeaveList);

        return "sick-leaves";
    }

    @GetMapping("/addSickLeave/{teacherId}")
    public String showSickLeaveForm(@PathVariable int teacherId, Model theModel) {

        Teacher theTeacher = teacherService.getTeacher(teacherId);

        SickLeave sickLeave = new SickLeave();


        theModel.addAttribute("teacher", theTeacher);
        theModel.addAttribute("sickLeave", sickLeave);


        return "sickleave-form";
    }

    @PostMapping("/saveSickLeave/{teacherId}")
    public String saveSickLeave(@ModelAttribute("sickLeave") SickLeave sickLeave, @PathVariable int teacherId) {

        teacherService.saveTeachersSickLeave(teacherId, sickLeave);

        return "redirect:/teacher/sickLeaves/{teacherId}";
    }


}


