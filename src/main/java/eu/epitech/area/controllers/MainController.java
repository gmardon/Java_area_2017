package eu.epitech.area.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @RequestMapping("/")
    public String dashboard(@RequestParam(value="test", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("test", "Hello World !");
        return "dashboard";
    }
}
