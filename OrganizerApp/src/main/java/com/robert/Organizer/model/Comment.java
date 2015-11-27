package com.robert.Organizer.model;

import java.sql.Timestamp;

/**
 * Created by RobertXi on 10/5/15.
 */
public class Comment extends OrganizerSuperClass{

//    private int id;
    private int taskitem_id;
//    private String title;//the content
//    private String description = null;
//    private Timestamp date_created;
//    private int user_id;
//    private String type = "comment";

    public Comment(){
        super.setType("comment");
    }


    public int getTaskitem_id() {return taskitem_id;}

    public void setTaskitem_id(int taskitem_id) {
        this.taskitem_id = taskitem_id;
    }

}
