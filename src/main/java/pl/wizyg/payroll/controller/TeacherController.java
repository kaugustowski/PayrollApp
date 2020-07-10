package pl.wizyg.payroll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.Education;
import pl.wizyg.payroll.entity.Teacher;
import pl.wizyg.payroll.entity.TeacherType;
import pl.wizyg.payroll.service.EmployeeService;
import pl.wizyg.payroll.service.TeacherService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/teacher")
public class TeacherController {

    Logger logger = LoggerFactory.getLogger(TeacherController.class);



    private final TeacherService teacherService;
    private final EmployeeService employeeServiceService;


    public TeacherController(TeacherService teacherService, EmployeeService employeeServiceService) {
        this.teacherService = teacherService;
        this.employeeServiceService = employeeServiceService;
    }


    @GetMapping("/list")
    public String listTeachers(Model theModel) {

        List<Teacher> theTeachers = teacherService.getTeachers();

        theModel.addAttribute("teachers", theTeachers);

        return "list-teachers";
    }

    @GetMapping("/list/active")
    public String listActiveTeachers(Model theModel) {

        List<Teacher> theTeachers = teacherService.getActiveTeachers();

        theModel.addAttribute("teachers", theTeachers);

        return "list-teachers";
    }

    @GetMapping("/list/inactive")
    public String listInactiveTeachers(Model theModel) {

        List<Teacher> theTeachers = teacherService.getInactiveTeachers();

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
    public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model theModel) {

        if (bindingResult.hasErrors()) {


            Education[] education = Education.values();

            TeacherType[] teacherTypes = TeacherType.values();

            theModel.addAttribute("teacher", teacher);
            theModel.addAttribute("educationValues", education);
            theModel.addAttribute("teacherTypeValues", teacherTypes);
            return "teacher-form";
        }

        teacherService.saveTeacher(teacher);
        employeeServiceService.createEmployeeAccountIfDoesNotExist(teacher);

        return "redirect:/teacher/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("teacherId") int theId,
                                    Model theModel) {

        Teacher theTeacher = teacherService.getTeacher(theId);
        Education[] education = Education.values();
        TeacherType[] teacherTypes = TeacherType.values();

        theModel.addAttribute("teacher", theTeacher);
        theModel.addAttribute("educationValues", education);
        theModel.addAttribute("teacherTypeValues", teacherTypes);
        return "teacher-form";
    }


    @GetMapping("/setInactive")
    public String setTeacherInactive(@RequestParam("teacherId") int theId) {

        // delete the customer
        teacherService.setInactiveTeacher(theId);

        return "redirect:/teacher/list";
    }

    @GetMapping("/setActive")
    public String setTeacherActive(@RequestParam("teacherId") int theId) {

        // delete the customer
        teacherService.setActiveTeacher(theId);

        return "redirect:/teacher/list";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
        System.out.println((e.getMessage() + Arrays.toString(e.getStackTrace()) + "Returning HTTP 400 Bad Request" + e));
    }
}


