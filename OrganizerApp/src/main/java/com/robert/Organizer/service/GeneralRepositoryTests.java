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
            convertTaskToEntry(task);
        }
        for(TaskItem item : taskItems){
            convertTaskItemToEntry(item);
        }
        for(Comment comment : comments){
            convertCommentToEntry(comment);
        }
    }

    private void convertCommentToEntry(Comment task){
        TaskEntry entry = new TaskEntry();
        entry.setDate_created(task.getDate_created().getTime());
        entry.setTitle(task.getTitle());
        entry.setType(task.getType());
        entry.setUser_id(Integer.toString(task.getUser_id()));
        entry.setId(task.getTitle()+task.getId());
        taskEntryRepository.save(entry);
    }

    private void convertTaskItemToEntry(TaskItem item){
        TaskEntry entry = new TaskEntry();
        entry.setDate_created(item.getDate_created().getTime());
        entry.setDate_modified(item.getDate_modified().getTime());
        entry.setDescription(item.getDescription());
        entry.setTitle(item.getTitle());
        entry.setType(item.getType());
        entry.setUser_id(Integer.toString(item.getUser_id()));
        entry.setId(item.getTitle() + item.getId());
        taskEntryRepository.save(entry);
    }
    private void convertTaskToEntry(Task task){
        TaskEntry entry = new TaskEntry();
        entry.setDate_created(task.getDate_created().getTime());
        entry.setDate_modified(task.getDate_modified().getTime());
        entry.setDescription(task.getDescription());
        entry.setTitle(task.getTitle());
        entry.setType(task.getType());
        entry.setUser_id(Integer.toString(task.getUser_id()));
        entry.setId(task.getTitle() + task.getId());
        taskEntryRepository.save(entry);
    }

    public void findByTitleOrDescription(){
        System.out.println("FINDING ENTRY BY TITLE OR DESCRIPTION");
        Page<TaskEntry> entryPage = taskEntryRepository.findByTitleOrDescription("test", "test", new PageRequest(0, 10));
        List<TaskEntry> entries = entryPage.getContent();

        for ( TaskEntry entry : entries){
            System.out.println(entry.toString());
        }
    }


}
