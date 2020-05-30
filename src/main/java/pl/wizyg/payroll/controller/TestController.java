package pl.wizyg.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wizyg.payroll.DAO.TestDAO;
import pl.wizyg.payroll.entity.Test;
import pl.wizyg.payroll.repository.TestRepository;
import pl.wizyg.payroll.service.TestService;

@Controller
public class TestController {

    @Autowired
    TestDAO testDAO;
    @Autowired
    TestService testService;
    @Autowired
    TestRepository testRepository;

    @GetMapping("/testForm")
    public String showTestForm(Model model) {

        Test test = new Test();

        model.addAttribute("testObj", test);


        return "test-form";

    }

    @PostMapping("/saveTest")
    public String addTest(@ModelAttribute Test test) {

      //  testService.saveTest(test);

        testRepository.save(test);

        System.out.println(test.getDate());
        return "redirect:/testForm";

    }

}
