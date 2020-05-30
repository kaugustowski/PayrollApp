package pl.wizyg.payroll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.*;
import pl.wizyg.payroll.repository.SickLeaveRepository;
import pl.wizyg.payroll.service.TeacherService;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/teacher")
public class TeacherController {

    Logger logger = LoggerFactory.getLogger(TeacherController.class);
    // private static final Logger logger = Logger.getLogger(TeacherController.class);


    private final SickLeaveRepository sickLeaveRepository;
    private final TeacherService teacherService;

    public TeacherController(SickLeaveRepository sickLeaveRepository, TeacherService teacherService) {
        this.sickLeaveRepository = sickLeaveRepository;
        this.teacherService = teacherService;
    }


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
        TeacherType[] teacherTypes = TeacherType.values();

        theModel.addAttribute("teacher", theTeacher);
        theModel.addAttribute("educationValues", education);
        theModel.addAttribute("teacherTypeValues", teacherTypes);
        return "teacher-form";
    }

    @GetMapping("/delete")
    public String deleteTeacher(@RequestParam("teacherId") int theId) {

        // delete the customer
        teacherService.deleteTeacher(theId);

        return "redirect:/teacher/list";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
        System.out.println((e.getMessage() + Arrays.toString(e.getStackTrace()) + "Returning HTTP 400 Bad Request" + e));
    }
}


