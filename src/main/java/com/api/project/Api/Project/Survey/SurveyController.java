package com.api.project.Api.Project.Survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SurveyController {
    @Autowired
    SurveyRepository srepo;

    @RequestMapping("/dummy")
    public String getDummyPage() {

        ModelAndView modelAndView = new ModelAndView();

        System.out.println("HIIII ");

        int user_id = 1;
        List<Survey> listSurvey = srepo.showAllSurvey(user_id);

        System.out.println("survey = " + listSurvey.get(0).getLocation_id());


        modelAndView.setViewName("dummy"); // resources/template/home.html
        return "dummy";
    }
}