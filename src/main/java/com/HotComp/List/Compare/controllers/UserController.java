package com.HotComp.List.Compare.controllers;

import com.HotComp.List.Compare.models.User;
import com.HotComp.List.Compare.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("user")
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
    public String validateUser(Model model,@RequestParam String userName,@RequestParam String password){
        model.addAttribute("header","Login");
        // get user data from DB if exists
        List<User> findUser = userDao.findAllByUserName(userName);
        // if user exists check to see if password matches
        if (findUser.size() >=1 && findUser.get(0).getPassword().equals(password)){
            return "user/working";
        }

        return "user/index";
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
