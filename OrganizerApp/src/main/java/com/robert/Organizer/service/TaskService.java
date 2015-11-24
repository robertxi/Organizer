package com.robert.Organizer.service;

import com.robert.Organizer.model.Task;
import com.robert.Organizer.model.TaskItem;

import java.util.List;

/**
 * Created by RobertXi on 11/24/15.
 */
public class TaskService {


    public static void setId(Task task, int id){
        task.setId(id);
    }
    public static int getId(Task task){
        return task.getId();
    }
    public static List<TaskItem> getTaskItems(int task_id){
        List<TaskItem> retList = OrganizerDAO.getTaskItems(task_id);
        for(TaskItem item: retList){
            item.setCommentList(OrganizerDAO.getCommentList(item.getId()));
        }
        return retList;
    }
    public static void deleteTask(int task_id){
        OrganizerDAO.deleteTask(task_id);
    }
    public static List<Task> getTaskList(int user_id){
        List<Task> retList = OrganizerDAO.getTaskList(user_id);
        if(null!=retList) {
            for (Task item : retList) {
                item.setTaskListSize(item.getTaskList().size());
            }
        }
        return retList;
    }
    public static void addNewTask(Task task){
        OrganizerDAO.addNewTask(task);
    }

    public static Task getTask(int task_id){
        Task ret = OrganizerDAO.getTaskById(task_id);
        List<TaskItem> itemList = TaskService.getTaskItems(ret.getId());
        ret.setTaskList(itemList);
        ret.setTaskListSize(itemList.size());
        return ret;
    }
    public static Task getTaskByTitle(int user_id, String title){
        return OrganizerDAO.getTaskByTitle(user_id, title);
    }
}
