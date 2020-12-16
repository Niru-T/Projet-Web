package com.api.project.Api.Project.Survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class SurveyController {
    @Autowired
    SurveyRepository srepo;

    @RequestMapping("/createSurvey")
    public ModelAndView getCreateSurvey(HttpSession session)
    {
        if(session.getAttribute("user_id")!=null){
            return new ModelAndView("createSurvey");
        }
        else{
            ModelAndView error = new ModelAndView("problem");
            error.addObject("message","Please login or register first");
            return error;
        }
    }

    @RequestMapping("/mainPage")
    public ModelAndView getMainPage(HttpSession session)
    {
        if(session.getAttribute("user_id")!=null){
            return new ModelAndView("mainPage");
        }
        else{
            ModelAndView error = new ModelAndView("problem");
            error.addObject("message","Please login or register first");
            return error;
        }
    }

    @PostMapping("/createSurvey")
    public ModelAndView addSurvey(Survey survey, @RequestParam("date_proposed") @DateTimeFormat(pattern= "yyyy-MM-dd") Date date, HttpSession session)
    {
        ModelAndView success=new ModelAndView("surveySuccess");
        survey.setDate_proposed(date);
        survey.setUser_id(Integer.parseInt(session.getAttribute("user_id").toString()));
        srepo.save(survey);
        success.addObject("message","Survey successfully added !");
        return success;
    }

    @RequestMapping(value = { "/allSurvey" }, method = RequestMethod.GET)
    public ModelAndView showSurvey()
    {
        ModelAndView allSurveyView=new ModelAndView("allSurvey");
        List<Survey> allSurvey = srepo.getAllSurvey();
        allSurveyView.addObject("allSurvey", allSurvey);
        return allSurveyView;
    }

    @RequestMapping(value = { "/mySurvey" }, method = RequestMethod.GET)
    public ModelAndView showLoggedUserSurvey(HttpSession session)
    {
        ModelAndView mySurveyView=new ModelAndView("mySurvey");
        List<Survey> mySurvey = srepo.getUserSurvey(Integer.parseInt(session.getAttribute("user_id").toString()));
        mySurveyView.addObject("mySurvey", mySurvey);
        return mySurveyView;
    }
}