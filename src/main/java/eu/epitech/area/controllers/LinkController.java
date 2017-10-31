package eu.epitech.area.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LinkController {
    @RequestMapping("/links")
    public String links(Model model) {
        return "links";
    }

    @RequestMapping(value = "/link/create", method = RequestMethod.GET)
    public String create_link(Model model) {
        return "create_link";
    }
}
