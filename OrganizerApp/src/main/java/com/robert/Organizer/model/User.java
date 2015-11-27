package com.robert.Organizer.model;

import java.sql.Timestamp;

/**
 * Created by RobertXi on 11/24/15.
 */
public class User extends OrganizerSuperClass{


    //private int id;
    private String username;
    private String password;
    private String fName;
    private String lName;
    private String email;
    //private Timestamp date_created;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
