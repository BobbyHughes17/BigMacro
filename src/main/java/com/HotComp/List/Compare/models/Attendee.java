package com.HotComp.List.Compare.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Attendee {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private Date checkInDate;

    private Date checkOutDate;

    @ManyToOne
    private RoomingList roomingList;



    public Attendee() {
    }

    public Attendee(@NotNull String firstName, @NotNull String lastName, @NotNull Date checkInDate, @NotNull Date checkOutDate) {


        this.firstName = firstName;
        this.lastName = lastName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.checkInDate = format.parse(checkInDate);
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) throws ParseException{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.checkOutDate = format.parse(checkOutDate);
    }

    public RoomingList getRoomingList() {
        return roomingList;
    }

    public void setRoomingList(RoomingList roomingList) {
        this.roomingList = roomingList;
    }
}
