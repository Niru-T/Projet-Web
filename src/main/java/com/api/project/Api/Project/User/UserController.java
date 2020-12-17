package com.api.project.Api.Project.User;

import com.api.project.Api.Project.Survey.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController
{
    @Autowired
    UserRepository urepo;

    @Autowired
    SurveyRepository srepo;

    @RequestMapping("/")
    public String home()
    {
      return "home";
    }

    @RequestMapping("/signup")
    public String getSignup()
    {
        return "signup";
    }

    @RequestMapping("/loginPage")
    public ModelAndView getLoginPage(HttpSession session)
    {
        if(session.getAttribute("user_id")==null){
            return new ModelAndView("loginPage");
        }
        else{
            ModelAndView error = new ModelAndView("accesDenied");
            error.addObject("message","You can't login without log out first");
            return error;
        }
    }

    @RequestMapping("/logoutPage")
    public ModelAndView getLogoutPage(HttpSession session)
    {
        if(session.getAttribute("user_id")!=null){
            return new ModelAndView("logoutPage");
        }
        else{
            ModelAndView error = new ModelAndView("problem");
            error.addObject("message","You can't logout if you're not logged in already");
            return error;
        }
    }

    @PostMapping("/signup")
    public ModelAndView addUser(@RequestParam("user_email") String user_email, User user)
    {
        ModelAndView success=new ModelAndView("success");
        ModelAndView echec=new ModelAndView("problem");
        List<User> list=urepo.findByEmail(user_email);

        if(list.size()!=0)
        {
            echec.addObject("message", "Un compte utilise déjà cet e-mail !");
            return echec;
        }
        else
        {
            urepo.save(user);
            success.addObject("message","Utilisateur enregistré avec succès !");
        }
        return success;
    }

    @PostMapping("/loginPage")
    public ModelAndView login_user(@RequestParam("username") String email, @RequestParam("password") String password,
                             HttpSession session)
    {
        ModelAndView mainPage=new ModelAndView("mainPage");
        ModelAndView loginFailed=new ModelAndView("problem");
        User auser=urepo.findByEmailAndPassword(email, password);

        if(auser!=null)
        {
            session.setAttribute("firstname", auser.getUser_fname());
            session.setAttribute("lastname", auser.getUser_lname());
            session.setAttribute("user_id", auser.getUser_id());
            return mainPage;
        }
        else
        {
            loginFailed.addObject("message", " Login failed !");
            return loginFailed;
        }
    }

    @PostMapping(value="/logoutPage")
    public ModelAndView logout(HttpSession session) {
        ModelAndView mv=new ModelAndView("home");
        session.removeAttribute("firstname");
        session.removeAttribute("lastname");
        session.invalidate();
        return mv;
    }

    @RequestMapping(value = { "/allUser" }, method = RequestMethod.GET)
    public ModelAndView showSurvey(HttpSession session)
    {
        if(session.getAttribute("user_id").equals(1)){
            ModelAndView allSurveyView=new ModelAndView("allUser");
            List<User> allUser = urepo.getAllUser();
            allSurveyView.addObject("allUser", allUser);
            return allSurveyView;
        }
        else{
            ModelAndView error = new ModelAndView("accesDenied");
            error.addObject("message","You have not the rights to access this page");
            return error;
        }
    }

    @RequestMapping(value = { "/allUser" }, method = RequestMethod.POST)
    public ModelAndView deleteMySurvey(@RequestParam ("user_id") String id, HttpSession session) {
        int user_id = Integer.parseInt(id);
        if(user_id!=1){ // 1 correspond to the user_id of the super user (admin) in our project so we can't delete that user.
            User user = urepo.findById(user_id).orElseThrow(() -> new IllegalArgumentException("Invalid survey Id:" + id));
            srepo.deleteAllSurveyOfUser(user_id);
            urepo.delete(user);
        }
        return new ModelAndView("redirect:/allUser");
    }

}
