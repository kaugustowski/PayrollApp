package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.wizyg.payroll.entity.AdministrativeEmployee;
import pl.wizyg.payroll.service.AdministrativeEmployeeService;

import java.util.List;


@Controller
@RequestMapping("/administrativeEmployee")
public class AdministrativeEmployeeController {


    @Autowired
    private AdministrativeEmployeeService administrativeEmployeeService;


    @GetMapping("/list")
    public String listAdministrativeEmployees(Model theModel) {

        List<AdministrativeEmployee> employees = administrativeEmployeeService.getAdministrativeEmployees();

        theModel.addAttribute("administrativeEmployees", employees);

        return "list-teachers";
    }

    @GetMapping("/addAdministrativeEmployee")
    public String addAdministrativeEmployee(Model theModel) {

        AdministrativeEmployee teacher = new AdministrativeEmployee();

        theModel.addAttribute("teacher", teacher);

        return "administrative-employee-form";

    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("administrativeEmployee") AdministrativeEmployee administrativeEmployee) {

        // save the customer using our service
        administrativeEmployeeService.saveAdministrativeEmployee(administrativeEmployee);

        return "redirect:/administrativeEmployee/addAdministrativeEmployee";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("administrativeEmployeeId") int theId,
                                    Model theModel) {

        AdministrativeEmployee administrativeEmployee = administrativeEmployeeService.getAdministrativeEmployee(theId);

        theModel.addAttribute("administrativeEmployee", administrativeEmployee);

        return "administrative-employee-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("administrativeEmployeeId") int theId) {


        administrativeEmployeeService.deleteAdministrativeEmployee(theId);

        return "redirect:/administrativeEmployee/list";
    }

}


