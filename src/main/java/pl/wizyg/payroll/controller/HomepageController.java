package pl.wizyg.payroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomepageController {

    @GetMapping("/")
    public String showHome() {

        return "home";
    }
}
