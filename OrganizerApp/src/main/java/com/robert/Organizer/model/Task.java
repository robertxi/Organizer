package com.robert.Organizer.model;

import com.robert.Organizer.model.TaskItem;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by RobertXi on 10/5/15.
 */
public class Task extends OrganizerSuperClass{

//    private int id;
//    private int user_id;
//    private String title;
//    private String description;
//    private Timestamp date_created;
//    private Timestamp date_modified;
    private List<TaskItem> TaskList;
    private int taskListSize;
//    private String type = "task";

    public Task(){
        super.setType("task");
    }

    public int getTaskListSize() {return taskListSize;}

    public void setTaskListSize(int taskListSize) {this.taskListSize = taskListSize;}

    public List<TaskItem> getTaskList() {
        return TaskList;
    }

    public void setTaskList(List<TaskItem> taskList) {
        TaskList = taskList;
    }


}
