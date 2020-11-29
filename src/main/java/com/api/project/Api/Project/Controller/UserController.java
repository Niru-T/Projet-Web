package com.api.project.Api.Project.Controller;

import com.api.project.Api.Project.User;
import com.api.project.Api.Project.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
        List<User> list=urepo.findByEmail(user_email);

        if(list.size()!=0)
        {
            mv.addObject("message", "Un compte utilise déà cet e-mail !");

        }
        else
        {
            urepo.save(user);
            mv.addObject("message","Utilisateur enregistré avec succès !");
        }

        return mv;
    }
    @GetMapping("/dummy")
    public String dummy()
    {
        return "dummy";
    }

    @PostMapping("/loginPage")
    public String login_user(@RequestParam("username") String email, @RequestParam("password") String password,
                             HttpSession session, ModelMap modelMap)
    {

        User auser=urepo.findByEmailAndPassword(email, password);

        if(auser!=null)
        {
            return "dummy";
        }
        else
        {
            modelMap.put("error", "Compte Invalide");
            return "loginPage";
        }

    }

    @GetMapping(value = "/logout")
    public String logout_user(HttpSession session)
    {
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:/loginPage";
    }


}
