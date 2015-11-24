package com.robert.Organizer.service;

import com.robert.Organizer.model.TaskItem;

/**
 * Created by RobertXi on 11/24/15.
 */
public class TaskItemService {

    public static void removeTaskItem(int taskItem_id){
        OrganizerDAO.removeTaskItemComments(taskItem_id);
        OrganizerDAO.removeTaskItem(taskItem_id);
    }
    public static void addTaskItem(TaskItem item){
        OrganizerDAO.addTaskItem(item);
    }

    public static TaskItem updateTaskItem(TaskItem item){
        TaskItem ret = OrganizerDAO.updateTaskItem(item);
        return ret;
    }
    public static TaskItem getTaskItem(int id){
        TaskItem ret = OrganizerDAO.getTaskItemById(id);
        return ret;
    }

}
