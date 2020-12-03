package com.api.project.Api.Project.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/logoutPage")
    public String getLogoutPage()
    {
        return "logoutPage";
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
        ModelAndView loginSuccess=new ModelAndView("success");
        ModelAndView loginFailed=new ModelAndView("problem");
        User auser=urepo.findByEmailAndPassword(email, password);

        if(auser!=null)
        {
            session.setAttribute("firstname", auser.getUser_fname());
            session.setAttribute("lastname", auser.getUser_lname());
            session.setAttribute("user_id", auser.getUser_id());
            loginSuccess.addObject("message","Login success !");
            return loginSuccess;
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


}
