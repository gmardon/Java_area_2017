package eu.epitech.area.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/links")
public class LinkController {
    @RequestMapping("")
    public String links(Model model) {
        return "links";
    }
}
