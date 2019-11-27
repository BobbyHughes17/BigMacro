package com.HotComp.List.Compare.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements Serializable    {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3,max=15,message = "Must be between 3 and 15 characters")
    private String userName;

    @NotNull
    @Size(min=3,max=15,message = "Must be between 3 and 15 characters")
    private String password;

    @NotNull(message = "Email must not be blank")
    @Email(message = "Must be a valid email address")
    private String email;

    @OneToMany
    @JoinColumn(name = "User_id")
    private List<RoomingList> roomingLists = new ArrayList<>();

    public User(){
    }

    public User(String userName, String password, String email){
        this();
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean verifyPassword(String password){
        if (!this.password.equals(password)){
            return  true;
        }
        return false;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
