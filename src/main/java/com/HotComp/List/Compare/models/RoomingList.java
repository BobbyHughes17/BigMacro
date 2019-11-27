package com.HotComp.List.Compare.models;


import com.HotComp.List.Compare.models.data.AttendeeDao;
import com.sun.javafx.beans.IDProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Parser;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class RoomingList {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3,max=20)
    private String listName;

    @NotNull
    @DateTimeFormat
    private Date uploadDate;

    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name ="roomingList_id")

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoomingList(){}

    public RoomingList(@NotNull @Size(min = 3, max = 20) String listName, @NotNull Date uploadDate, List<Attendee> attendees) {
        this.listName = listName;
        this.uploadDate = uploadDate;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
