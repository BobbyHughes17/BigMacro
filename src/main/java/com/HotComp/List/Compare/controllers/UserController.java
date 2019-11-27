package com.HotComp.List.Compare.controllers;

import com.HotComp.List.Compare.models.User;
import com.HotComp.List.Compare.models.data.UserDao;
import org.apache.coyote.Request;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("user")
@SessionAttributes("curUser")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("header","Login");
        model.addAttribute("users",userDao.findAll());
        return "user/index";
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String validateUser(ModelMap model, @RequestParam String userName, @RequestParam String password,HttpServletRequest request){
        model.addAttribute("header","Login");
        // get user data from DB if exists
        List<User> findUser = userDao.findAllByUserName(userName);
        User user = findUser.get(0);
        // if user exists check to see if password matches
        if (findUser.size() >=1 && findUser.get(0).getPassword().equals(password)){
            model.addAttribute("curUser",findUser.get(0));
            return "redirect:welcome";
        }

        return "user/index";
    }

    @RequestMapping(value = "welcome", method=RequestMethod.GET)
    public String welcomePage(ModelMap model){
        return "user/working";
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.GET)
    public String signUp(Model model, @ModelAttribute User user){
        model.addAttribute("header","Sign Up");
        model.addAttribute("users",userDao.findAll());
        model.addAttribute(new User());
        return "user/sign-up";
    }

    @RequestMapping(value = "sign-up",method = RequestMethod.POST)
    public String validSignUp(Model model, @ModelAttribute("user") @Valid User user,Errors errors, @RequestParam String verPass){

        List<User> findUser = userDao.findAllByUserName(user.getUserName());
        List<User> findEmail = userDao.findAllByEmail(user.getEmail());
        String userError = "";
        String emailError = "";
        String passMatchError = "";

        if (findUser.size() >=1){
            userError = "This user name already exists.";
            model.addAttribute("userError",userError);
        }
        if (findEmail.size() >=1){
            emailError = "This email address already exists.";
            model.addAttribute("emailError", emailError);
        }
        if (user.verifyPassword(verPass)){
            passMatchError = "Your passwords don't match.";
            model.addAttribute("passMatchError",passMatchError);
        }
        if((errors.hasErrors() || !userError.isEmpty() || !emailError.isEmpty() || !passMatchError.isEmpty())){
            model.addAttribute(errors);
            return "user/sign-up";
        }

        userDao.save(user);
        return  "redirect:";
    }
}
