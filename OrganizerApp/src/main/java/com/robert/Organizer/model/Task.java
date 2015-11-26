package com.robert.Organizer.model;

import com.robert.Organizer.model.TaskItem;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by RobertXi on 10/5/15.
 */
public class Task extends OrganizerSuperClass{

    private int id;
    private int user_id;
    private String title;
    private String description;
    private Timestamp date_created;
    private Timestamp date_modified;
    private List<TaskItem> TaskList;
    private int taskListSize;
    private String type = "task";

    public String getType(){
        return type;
    }

    public int getTaskListSize() {return taskListSize;}

    public void setTaskListSize(int taskListSize) {this.taskListSize = taskListSize;}

    public List<TaskItem> getTaskList() {
        return TaskList;
    }

    public void setTaskList(List<TaskItem> taskList) {
        TaskList = taskList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }

    public Timestamp getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Timestamp date_modified) {
        this.date_modified = date_modified;
    }


}
