package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO search all categories doesn't return any results!!
    @RequestMapping(value ="results")
    public String searchResults(@RequestParam String searchTerm, @RequestParam String searchType, Model model){

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);

        if (searchType.equals("all")){
            jobs = JobData.findByValue(searchTerm);
        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);

        return "search";
    }

}
