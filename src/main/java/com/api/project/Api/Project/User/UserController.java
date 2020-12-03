package com.api.project.Api.Project.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController
{
    @Autowired
    UserRepository urepo;

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
    public String getLoginPage()
    {
        return "loginPage";
    }

    @PostMapping("/signup")
    public ModelAndView addUser(@RequestParam("user_email") String user_email, User user)
    {
        ModelAndView mv=new ModelAndView("success");
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
            mv.addObject("message","Utilisateur enregistré avec succès !");
        }
        return mv;
    }

    @PostMapping("/loginPage")
    public String login_user(@RequestParam("username") String email, @RequestParam("password") String password,
                             HttpSession session, ModelMap modelMap)
    {

        User auser=urepo.findByEmailAndPassword(email, password);

        if(auser!=null)
        {
            session.setAttribute("firstname", auser.getUser_fname());
            session.setAttribute("lastname", auser.getUser_lname());
            return "dummy";
        }
        else
        {
            modelMap.put("error", "Compte Invalide");
            return "loginPage";
        }

    }

    /*@GetMapping(value = "/dummy")
    public String logout_user(HttpSession session)
    {
        session.removeAttribute("firstname");
        session.removeAttribute("lastname");
        session.invalidate();
        return "loginPage";
    }*/


}
