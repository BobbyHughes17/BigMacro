package com.HotComp.List.Compare.controllers;

import com.HotComp.List.Compare.models.Attendee;
import com.HotComp.List.Compare.models.RoomingList;
import com.HotComp.List.Compare.models.User;
import com.HotComp.List.Compare.models.data.AttendeeDao;
import com.HotComp.List.Compare.models.data.RoomingDao;
import com.HotComp.List.Compare.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
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

   @RequestMapping(value="/{listId}/add-attendee", method = RequestMethod.GET)
   public String addAttendee(Model model, @PathVariable int listId,@ModelAttribute("attendee") Attendee attendee){
        String listName = roomingDao.findOneById(listId).getListName();
        model.addAttribute("listName",listName);
        model.addAttribute(new Attendee());
        return "RoomingList/addAttendee";
   }

   @RequestMapping(value="/{listId}/add-attendee",method = RequestMethod.POST)
   public String validateAttendee(Model model, @PathVariable int listId, @ModelAttribute("attendee")Attendee attendee, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute(attendee);
            return "RoomingList/addAttendee";
        }
        attendee.setRoomingList(roomingDao.findOneById(listId));
        attendeeDao.save(attendee);
        return "redirect:";
   }

    @RequestMapping(value = "{listId}",method = RequestMethod.GET)
    public String listAttendees(Model model, @PathVariable int listId){
        model.addAttribute("thisList",attendeeDao.findAllByRoomingList_Id(listId));
        return "RoomingList/displayList";
    }

}
