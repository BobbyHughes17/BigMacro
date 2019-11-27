package com.HotComp.List.Compare.controllers;

import com.HotComp.List.Compare.models.RoomingList;
import com.HotComp.List.Compare.models.User;
import com.HotComp.List.Compare.models.data.AttendeeDao;
import com.HotComp.List.Compare.models.data.RoomingDao;
import com.HotComp.List.Compare.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("lists")
@SessionAttributes("curUser")
public class RoomingListController {

    @Autowired
    private RoomingDao roomingDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AttendeeDao attendeeDao;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        User curUser = (User)request.getSession(true).getAttribute("curUser");
        model.addAttribute("curUser",curUser);
        model.addAttribute("userLists",roomingDao.findAllByUser_id(curUser.getId()));
        return "RoomingList/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        return "RoomingList/addList";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String addList(Model model, @RequestParam String listName,HttpServletRequest request){
        if(listName.length()<1){
            String listNameError = "You need to input a name for the List";
            return "RoomingList/addList";
        }
        RoomingList roomingList = new RoomingList();
        User curUser = (User)request.getSession(true).getAttribute("curUser");
        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        Date date = new Date();


        roomingList.setListName(listName);
        roomingList.setUser(curUser);
        roomingList.setUploadDate(date);
        roomingDao.save(roomingList);

        return "redirect:";
    }

    @RequestMapping(value = "listId=${listId}", method = RequestMethod.GET)
    public String viewLIst(Model model, @RequestParam int listId){
        model.addAttribute("thisList",attendeeDao.findAllByRoomingList_Id(listId));
        return "RoomingList/displayList";
    }

    @RequestMapping(value = "listId=${listId}/add",method = RequestMethod.GET)
    public String addAttendee(Model model){
        return "RoomingList/addAttendee";
    }


}
