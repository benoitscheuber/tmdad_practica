package es.unizar.tmdad.lab0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.unizar.tmdad.lab0.service.TwitterLookupService;


@Controller
public class SearchController {

    @Autowired
    TwitterLookupService twitter;

    @RequestMapping("/")
    public String greeting() {
        return "index";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("q") String q, Model m) {
    	m.addAttribute("res", twitter.search(q));
        return "search :: content";
    }
}