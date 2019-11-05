package com.ListCompare.BigMacro.controllers;

import com.ListCompare.BigMacro.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao

    @RequestMapping(value = "")
    public String index(Model model){
        return index()
    }
}
