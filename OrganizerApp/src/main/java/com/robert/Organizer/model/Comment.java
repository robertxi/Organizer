package com.robert.Organizer.model;

import java.sql.Timestamp;

/**
 * Created by RobertXi on 10/5/15.
 */
public class Comment extends OrganizerSuperClass{

    private int id;
    private int taskitem_id;
    private String title;//the content
    private String description = null;
    private Timestamp date_created;
    private int user_id;
    private String type = "comment";

    public String getType(){
        return type;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getUser_id() {return user_id;}

    public void setUser_id(int user_id) {this.user_id = user_id;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskitem_id() {return taskitem_id;}

    public void setTaskitem_id(int taskitem_id) {
        this.taskitem_id = taskitem_id;
    }

    public Timestamp getDate_created() {return date_created;}

    public void setDate_created(Timestamp date_create) {this.date_created = date_create;}

}
