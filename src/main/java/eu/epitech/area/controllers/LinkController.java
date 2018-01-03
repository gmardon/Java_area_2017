package eu.epitech.area.controllers;

import eu.epitech.area.service.LinkService;
import eu.epitech.area.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LinkController {
    @Autowired
    private LinkService linkService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping("/links")
    public String links(Model model) {
        model.addAttribute("links", linkService.getAllFromUser(securityService.getLoggedUser()));
        return "links";
    }

    @RequestMapping(value = "/link/create", method = RequestMethod.GET)
    public String create_link_page(Model model) {
        return "create_link";
    }

    @RequestMapping(value = "/link/create", method = RequestMethod.POST)
    public String create_link(@RequestParam Map<String,String> params, Model model) {
        linkService.create(params.get("action.name"),
                            params.get("action.params").split("\n"),
                params.get("reaction.name"),
                params.get("reaction.params").split("\n"),
                securityService.getLoggedUser());
        return "links";
    }
}
