package com.HotComp.List.Compare.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Date checkInDate;

    @NotNull
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

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public RoomingList getRoomingList() {
        return roomingList;
    }

    public void setRoomingList(RoomingList roomingList) {
        this.roomingList = roomingList;
    }
}
