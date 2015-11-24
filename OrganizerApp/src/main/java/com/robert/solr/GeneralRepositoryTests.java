package com.robert.solr;


import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by RobertXi on 11/22/15.
 */
@Service
@Configurable
public class GeneralRepositoryTests {

//    @Autowired
//    private TaskRepository taskRepository;

    @Autowired
    private TaskEntryRepository taskEntryRepository;



    public void run(){
        createSampleData();
//        findByTitle();
        findByTitleOrDescription();

    }

//    private void addTaskToIndex(String id, String title, String description){
//        Task task = new Task();
////        List<TaskItem> list = new ArrayList<>();
//
//        task.setId(id);
//        task.setTitle(title);
//        task.setDescription(description);
//        taskRepository.save(task);
//    }

    private void addTaskEntryToIndex(String id, String title, String description, String type, String user_id, long date_created, long date_modified){
        TaskEntry entry = new TaskEntry();
        entry.setId(type+id);
        entry.setTitle(title);
        entry.setDescription(description);
        entry.setType(type);
        entry.setUser_id(user_id);
        entry.setDate_created(date_created);
        entry.setDate_modified(date_modified);
        taskEntryRepository.save(entry);

    }

    private void createSampleData() {
        if(taskEntryRepository.count()==0){
            addTaskEntryToIndex("2", "workout", "get pumped!", "Task", "1", System.currentTimeMillis(), System.currentTimeMillis() );
            addTaskEntryToIndex("3", "Melissa's Birthday", "plan out the party!", "Task", "1", System.currentTimeMillis(), System.currentTimeMillis());
            addTaskEntryToIndex("4", "put turkey in oven", "to do", "TaskItem", "3", System.currentTimeMillis(), System.currentTimeMillis() );
            addTaskEntryToIndex("1", "Wow, that's great!", "", "Comment", "3", System.currentTimeMillis(), System.currentTimeMillis() );
        }

//        if(taskRepository.count()==0) {
//
//            System.out.println(taskRepository.count());
//
//            System.out.println("ADDING TASKS TO REPOSITORY");
//            addTaskToIndex("11", "workout", "get pumped");
//            addTaskToIndex("12", "Melissa's birthday", "plan out the party");
//            addTaskToIndex("13", "winter Break!", "what to do");
//            addTaskToIndex("14", "bucket list", "life fun");
//            addTaskToIndex("15", "wedding plans", "lots to do for the big day!");
//            addTaskToIndex("16", "Thanksgiving break", "tasks to finish before thanksgiving");
//            System.out.println("FINISHED TASKS TO REPOSITORY");
//        }
    }

    public void findByTitleOrDescription(){
        System.out.println("FINDING ENTRY BY TITLE OR DESCRIPTION");
        Page<TaskEntry> entryPage = taskEntryRepository.findByTitleOrDescription("turkey", "party", new PageRequest(0, 10));
        List<TaskEntry> entries = entryPage.getContent();

        for ( TaskEntry entry : entries){
            System.out.println(entry.toString());
        }
    }
//
//    public void findByTitle(){
//        System.out.println("FINDING TASKS BY TITLE");
//        List<Task> tasks = taskRepository.findByTitle("break");
//        for(Task task: tasks){
//            System.out.println(task.toString());
//        }
//    }

}
