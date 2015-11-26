package com.robert.Organizer.controller;

import com.robert.Organizer.model.Comment;
import com.robert.Organizer.model.Task;
import com.robert.Organizer.model.TaskItem;
import com.robert.Organizer.model.User;
import com.robert.Organizer.service.*;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class HelloController {

    private Date date;
    private Timestamp timestamp;

    @RequestMapping(value = "/loginAuth", method = RequestMethod.POST)
    public JSONObject loginAuth(@RequestBody JSONObject obj) {
        String token;
        token = OrganizerDAO.loginAuth(obj);
        JSONObject ret = new JSONObject();
        ret.put("token", token);
        return ret;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public boolean getUser(@RequestBody String str) {
        boolean nameTaken = UserService.checkUsername(str);
        return nameTaken;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public void registerUser(@RequestBody JSONObject obj) {
        date = new Date();
        timestamp = new Timestamp(date.getTime());

        User user = new User();
        user.setUsername((String) obj.get("username"));
        user.setPassword((String) obj.get("password"));
        user.setfName((String) obj.get("fName"));
        user.setlName((String) obj.get("lName"));
        user.setEmail((String) obj.get("email"));
        user.setDate_created(timestamp);
        UserService.register(user);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Task remove(@RequestBody JSONObject obj) {
        int user_id=OrganizerDAO.ifAuth((String)obj.get("token"));
        if(user_id!=-1) {
            int taskItem_id=(int)obj.get("taskItem_id");

            TaskItemService.removeTaskItem(taskItem_id);
            Task ret = TaskService.getTask((int)obj.get("task_id"));
            ret.getTaskList();
            ret.getTaskListSize();
            return ret;
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    public void deleteTask(@RequestBody JSONObject obj) {
        if(OrganizerDAO.ifAuth((String)obj.get("token"))!=-1) {
            TaskService.deleteTask((int)obj.get("task_id"));
        }
    }

    @RequestMapping(value = "/init/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Task> initialize(@RequestParam("usrtoken") String token) {
        List<Task> retList;
        int user_id = OrganizerDAO.ifAuth(token);
        retList = TaskService.getTaskList(user_id);
        return retList;
    }

    @RequestMapping(value = "/addTaskItem", method = RequestMethod.POST)
    public Task addTaskItem(@RequestBody JSONObject obj) {
        date = new Date();
        timestamp = new Timestamp(date.getTime());

        int user_id=OrganizerDAO.ifAuth((String)obj.get("token"));
        if(user_id!=-1) {
            TaskItem item = new TaskItem();
            item.setTitle((String) obj.get("content"));
            item.setTask_id((int) obj.get("task_id"));
            item.setDescription((String) obj.get("status"));
            item.setDate_created(timestamp);
            item.setDate_modified(timestamp);
            item.setUser_id(user_id);
            TaskItemService.addTaskItem(item);
            Task ret = TaskService.getTask(item.getTask_id());
            ret.setTaskList(TaskService.getTaskItems(ret.getId()));
            ret.setTaskListSize(ret.getTaskList().size());
            return ret;
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/updateTaskItem", method = RequestMethod.PUT)
    public Task updateTaskItem(@RequestBody JSONObject obj) {
        int user_id=OrganizerDAO.ifAuth((String)obj.get("token"));
        date = new Date();
        timestamp = new Timestamp(date.getTime());
        if(user_id!=-1) {
            TaskItem item = new TaskItem();

            item.setTitle((String) obj.get("content"));
            item.setTask_id((int) obj.get("task_id"));
            item.setId((int) obj.get("id"));
            item.setDescription((String) obj.get("status"));
            item.setDate_modified(timestamp);
            item.setUser_id(user_id);
            TaskItemService.updateTaskItem(item);
            Task ret = TaskService.getTask(item.getTask_id());
            //again, 1 is just a temporary User ID
            ret.setTaskList(TaskService.getTaskItems(ret.getId()));
            ret.setTaskListSize(ret.getTaskList().size());
            return ret;
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/addNewTask", method = RequestMethod.POST)
    public Task addNewTask(@RequestBody JSONObject obj) {
        date = new Date();
        timestamp = new Timestamp(date.getTime());

        int user_id = OrganizerDAO.ifAuth((String) obj.get("token"));
        if (user_id != -1) {
            Task task = new Task();
            task.setTitle((String) obj.get("title"));
            task.setUser_id(user_id);
            task.setDate_created(timestamp);
            task.setDate_modified(timestamp);
            task.setDescription((String) obj.get("description"));
            TaskService.addNewTask(task);
            Task retList;
            //insert 1 as User ID for now, in future, will pass User object instead
            retList = TaskService.getTaskByTitle(user_id, task.getTitle());
            return retList;
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/addNewComment", method = RequestMethod.POST)
    public List<Comment> addNewComment(@RequestBody JSONObject obj) {
        date = new Date();
        timestamp = new Timestamp(date.getTime());

        int user_id=OrganizerDAO.ifAuth((String)obj.get("token"));
        if(user_id!=-1) {
            Comment comment = new Comment();
            comment.setTaskitem_id((int) obj.get("taskitem_id"));
            comment.setDate_created(timestamp);
            comment.setUser_id(user_id);
            comment.setTitle((String) obj.get("content"));
            List<Comment> retList = CommentService.addNewComment(comment);
            return retList;
        }else{
            return null;
        }
    }
}
