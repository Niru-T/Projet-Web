package com.api.project.Api.Project.Vote;

import com.api.project.Api.Project.Survey.Survey;
import com.api.project.Api.Project.Survey.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class VoteController {
    @Autowired
    VoteRepository vrepo;

    @Autowired
    SurveyRepository srepo;

    @RequestMapping(value = { "/allSurvey" }, method = RequestMethod.GET)
    public ModelAndView showSurvey(HttpSession session)
    {
        if(session.getAttribute("user_id")!=null){
            ModelAndView allSurveyView=new ModelAndView("allSurvey");
            List<Survey> allSurvey = srepo.getAllSurvey();
            allSurveyView.addObject("allSurvey", allSurvey);
            return allSurveyView;
        }
        else{
            ModelAndView error = new ModelAndView("problem");
            error.addObject("message","Please login or register first");
            return error;
        }
    }

    @RequestMapping(value = { "/allSurvey" }, method = RequestMethod.POST)
    public ModelAndView voteOnSurvey(@RequestParam("survey_id") String id) {
       if(vrepo.findBySurveyId(Integer.parseInt(id))==null){
           Vote v = new Vote();
           v.setSurvey_id(Integer.parseInt(id));
           v.setVote_count(1);
           vrepo.save(v);
       }else{
           vrepo.addVoteOnSurvey(Integer.parseInt(id));
       }
        return new ModelAndView("redirect:/allSurvey");
    }

}
