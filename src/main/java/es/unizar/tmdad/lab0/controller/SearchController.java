package es.unizar.tmdad.lab0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.unizar.tmdad.lab0.service.TwitterLookupService;


@Controller
public class SearchController{

    @Autowired
    TwitterLookupService twitter;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting() {
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public SearchResults search(@RequestParam("q") String q, Model m) {
    	Res r = new Res(twitter.search(q));;
        return r.getRes();
    }
}