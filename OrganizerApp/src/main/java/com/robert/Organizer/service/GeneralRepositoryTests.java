package com.robert.Organizer.service;


import java.util.*;


import com.robert.Organizer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@Configurable
public class GeneralRepositoryTests {

    @Autowired
    private TaskEntryRepository taskEntryRepository;



    public void init(){
        loadSampleData();
        findByTitleOrDescription();

    }

    private void loadSampleData(){
        List<TaskItem> taskItems = OrganizerDAO.getAllTaskItems();
        List<Task> tasks = OrganizerDAO.getAllTasks();
        List<Comment> comments = OrganizerDAO.getAllComments();

        convertToEntries(taskItems, tasks, comments);
    }

    private void convertToEntries(List<TaskItem> taskItems, List<Task> tasks, List<Comment> comments){
        for(Task task : tasks){
            convertObjectToEntry(task);
        }
        for(TaskItem item : taskItems){
            System.out.println(item.getDate_modified());
            convertObjectToEntry(item);
        }
        for(Comment comment : comments){
            System.out.println(comment.getDate_modified());
            convertObjectToEntry(comment);
        }
    }

    private void convertObjectToEntry(OrganizerSuperClass obj){
        TaskEntry entry = new TaskEntry();
        entry.setDate_created(obj.getDate_created().getTime());
        entry.setTitle(obj.getTitle());
        entry.setType(obj.getType());
        entry.setUser_id(Integer.toString(obj.getUser_id()));
        entry.setId(obj.getTitle() + obj.getId());

        //comment is missing date modified and description, this only runs for task and items
        if(null!= obj.getDate_modified() && null != obj.getDescription()){
            entry.setDate_modified(obj.getDate_modified().getTime());
            entry.setDescription(obj.getDescription());
        }
        taskEntryRepository.save(entry);
    }
    public List<TaskEntry> findByTitleOrDescription(){
        System.out.println("FINDING ENTRY BY TITLE OR DESCRIPTION");
        Page<TaskEntry> entryPage = taskEntryRepository.findByTitleOrDescription("test", "test", new PageRequest(0, 10));
        List<TaskEntry> entries = entryPage.getContent();

        for ( TaskEntry entry : entries){
            System.out.println(entry.toString());
        }
        return entries;
    }

    public List<Task> doTaskSearch(String query, int user_id){
        List<TaskEntry> list = taskEntryRepository.findByTitleOrDescription(query, query);
        List<Task> ret = null;
        for(TaskEntry entry : list){
            if(entry.getType().equals("task")){
                Task task = OrganizerDAO.getTaskByTitle(user_id, entry.getTitle());
                ret.add(task);
            }
        }
        return ret;
    }


}
