package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wizyg.payroll.DAO.TestDAO;
import pl.wizyg.payroll.entity.Test;

@Controller
public class TestController {

    @Autowired
    TestDAO testDAO;

    @GetMapping("/testForm")
    public String showTestForm(Model model) {

        Test test = new Test();

        model.addAttribute("testObj", test);


        return "test-form";

    }

    @PostMapping("/saveTest")
    public String addTest(@ModelAttribute Test test) {

        testDAO.saveTest(test);

        System.out.println(test.getDate());
        return "redirect:/testForm";

    }

}
