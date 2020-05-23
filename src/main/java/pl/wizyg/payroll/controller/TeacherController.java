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


    @Autowired
    private SickLeaveRepository sickLeaveRepository;
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

//    @PostMapping("/calculate")
//    public String calculate() {
//
//        List<Teacher> teachers = teacherService.getTeachers();
//        //teachers.forEach(Teacher::calculateSalary);
//
//        return "redirect:/teacher/list";
//    }

    @GetMapping("/sickLeaves/{employeeId}")
    public String showSickLeaves(@PathVariable int employeeId, Model theModel) {

        Teacher teacher = teacherService.getTeacher(employeeId);

        List<SickLeave> sickLeaveList = sickLeaveRepository.findAllByEmployeeId(employeeId);

        theModel.addAttribute("teacher", teacher);
        theModel.addAttribute("sickLeaves", sickLeaveList);

        return "sick-leaves";
    }

    @GetMapping("/addSickLeave/{employeeId}")
    public String showSickLeaveForm(@PathVariable int employeeId, Model theModel) {

        Teacher theTeacher = teacherService.getTeacher(employeeId);

        SickLeave sickLeave = new SickLeave();


        theModel.addAttribute("teacher", theTeacher);
        theModel.addAttribute("sickLeave", sickLeave);

        System.out.println("FORM fsdfzs");

        return "sickleave-form";
    }

    @PostMapping("/saveSickLeave/{employeeId}")
    public String saveSickLeave(@ModelAttribute("sickLeave") SickLeave sickLeave, @PathVariable int employeeId) {
        System.out.println("Saving nr 1!!!!!  From:" + sickLeave.getStartDate() + "  to: " + sickLeave.getEndDate());

        teacherService.saveTeachersSickLeave(employeeId, sickLeave);

        System.out.println("Saving!!!!!  From:" + sickLeave.getStartDate() + "  to: " + sickLeave.getEndDate());
        return "redirect:/teacher/sickLeaves/{employeeId}";
    }

    @GetMapping("/addOvertime/{employeeId}")
    public String showOvertimeForm(@PathVariable int employeeId, Model theModel) {

        Teacher theTeacher = teacherService.getTeacher(employeeId);

        Overtime ot = new Overtime();


        theModel.addAttribute("teacher", theTeacher);
        theModel.addAttribute("overtime", ot);

        System.out.println("FORM fsdfzs");

        return "overtime-form";
    }

    @PostMapping("/saveOvertime/{employeeId}")
    public String saveOvertime(@ModelAttribute("sickLeave") Overtime overtime, @PathVariable int employeeId) {

        teacherService.saveTeachersOvertime(employeeId, overtime);

        return "redirect:/addOvertime/{employeeId}";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.warn("Returning HTTP 400 Bad Request", e);
        System.out.println((e.getMessage() + Arrays.toString(e.getStackTrace()) + "Returning HTTP 400 Bad Request" + e));
    }


}


