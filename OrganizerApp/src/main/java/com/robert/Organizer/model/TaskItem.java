package com.robert.Organizer.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by RobertXi on 10/5/15.
 */
public class TaskItem extends OrganizerSuperClass{
//    private int id;
    private int task_id;
//    private String title;  //the title/content
//    private Timestamp date_created;
//    private Timestamp date_modified;
//    private String description;  //the status
    private List<Comment> commentList;
//    private String type = "taskItem";

    public TaskItem() {
        super.setType("taskItem");
    }

    public List<Comment> getCommentList() {return commentList;}

    public void setCommentList(List<Comment> commentList) {this.commentList = commentList;}

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {this.task_id = task_id;}

}
