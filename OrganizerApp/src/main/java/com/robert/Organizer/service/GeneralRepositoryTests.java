package com.robert.Organizer.service;


import java.sql.Timestamp;
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

    private Timestamp latestUpdate= null;

    public void init(){
        loadSampleData();
        //findByTitleOrDescription();

    }

    private void loadSampleData(){
        if(taskEntryRepository.count()==0) {

            System.out.println(latestUpdate);
            List<TaskItem> taskItems = OrganizerDAO.getAllTaskItems();
            List<Task> tasks = OrganizerDAO.getAllTasks();
            List<Comment> comments = OrganizerDAO.getAllComments();

            //finds the latest item updated in comments/tasks/taskitems using math.max(a, math.max(b, c))
            long templatestUpdate = Math.max(taskItems.get(0).getDate_modified().getTime(), Math.max(tasks.get(0).getDate_modified().getTime(), comments.get(0).getDate_created().getTime()));
            latestUpdate = new Timestamp(templatestUpdate);
            convertToEntries(taskItems, tasks, comments);
            System.out.println(latestUpdate);
        }
    }

    private void convertToEntries(List<TaskItem> taskItems, List<Task> tasks, List<Comment> comments){
        for(Task task : tasks){
            convertObjectToEntry(task);
        }
        for(TaskItem item : taskItems){
            convertObjectToEntry(item);
        }
        for(Comment comment : comments){
            convertObjectToEntry(comment);
        }
    }

    private void convertObjectToEntry(OrganizerSuperClass obj){
        TaskEntry entry = new TaskEntry();
        entry.setDate_created(obj.getDate_created().getTime());
        entry.setTitle(obj.getTitle());
        entry.setType(obj.getType());
        entry.setUser_id(Integer.toString(obj.getUser_id()));
        entry.setObj_id(obj.getId()+"");
        entry.setId(obj.getTitle() + obj.getId());

        //comment is missing date modified and description, this only runs for task and items
        if(null!= obj.getDate_modified() && null != obj.getDescription()){
            entry.setDate_modified(obj.getDate_modified().getTime());
            entry.setDescription(obj.getDescription());
        }
        taskEntryRepository.save(entry);
    }
//    public List<TaskEntry> findByTitleOrDescription(){
//        System.out.println("FINDING ENTRY BY TITLE OR DESCRIPTION");
//        Page<TaskEntry> entryPage = taskEntryRepository.findByTitleOrDescription("test", "test", new PageRequest(0, 10));
//        List<TaskEntry> entries = entryPage.getContent();
//
//        for ( TaskEntry entry : entries){
//            System.out.println(entry.toString());
//        }
//        return entries;
//    }

    public List<Task> doTaskSearch(String query, int user_id){
        List<TaskEntry> list = taskEntryRepository.findByTitleOrDescription(query, query);
        List<Task> ret = new ArrayList<>();
        for(TaskEntry entry : list){
            System.out.println("entry userID = " + entry.getUser_id() + " required UserID = " + Integer.toString(user_id));
            System.out.println(entry.getType());
            String type = entry.getType();
            if((type.equals("task") || type.equals("taskItem"))&& entry.getUser_id().equals(Integer.toString(user_id))) {
                if (type.equals("task")) {
                    Task task = OrganizerDAO.getTaskByTitle(user_id, entry.getTitle());
                    ret.add(task);
                }
                if(type.equals("taskItem")){
                    TaskItem item = OrganizerDAO.getTaskId(Integer.parseInt(entry.getObj_id()));
                    Task task = OrganizerDAO.getTaskById(item.getTask_id());
                    List<TaskItem> taskList = OrganizerDAO.getTaskItems(task.getId());
                    if(taskList!=null) {
                        task.setTaskList(taskList);
                        task.setTaskListSize(taskList.size());
                    }
                    ret.add(task);
                }
            }
        }
        return ret;
    }


    public void updateSolrRepository(){
        System.out.println("scheduled task");
        if(taskEntryRepository.count()!=0) {
            System.out.println(latestUpdate);
            List<OrganizerSuperClass> tasks = OrganizerDAO.getRecentTasks(latestUpdate);
            System.out.print("new tasks: " + tasks.size() + " ");
            List<OrganizerSuperClass> items = OrganizerDAO.getRecentItems(latestUpdate);
            System.out.print("new items:" + items.size() + " ");
            List<OrganizerSuperClass> comments = OrganizerDAO.getRecentComments(latestUpdate);
            System.out.println("new comments:" + comments.size());

            for (OrganizerSuperClass task : tasks) {
                task.setType("task");
                convertObjectToEntry(task);
                setLatestUpdate(task.getDate_modified());
            }
            for (OrganizerSuperClass item : items) {
                item.setType("taskItem");
                convertObjectToEntry(item);
                setLatestUpdate(item.getDate_modified());
            }
            for (OrganizerSuperClass comment : comments) {
                comment.setType("comment");
                convertObjectToEntry(comment);
                setLatestUpdate(comment.getDate_created());
            }
        }
    }
    private void setLatestUpdate(Timestamp newTime){
        if(newTime.getTime() > latestUpdate.getTime()){
            latestUpdate = newTime;
            System.out.println("new latestUpdate: " + latestUpdate);
        }
    }

}
