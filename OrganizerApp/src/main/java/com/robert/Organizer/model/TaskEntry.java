package com.robert.Organizer.model;

import org.apache.solr.client.solrj.beans.Field;

import java.sql.Timestamp;


public class TaskEntry {

    @Field
    private String id;

    @Field
    private String title;


    @Field
    private String obj_id;

    @Field
    private String description;

    @Field
    private String type;

    @Field
    private String user_id;

    @Field
    private long date_created;

    @Field
    private long date_modified;

    public String getObj_id() {return obj_id;}

    public void setObj_id(String obj_id) {this.obj_id = obj_id;}


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(long date_modified) {
        this.date_modified = date_modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getDate_created() {
        return date_created;
    }

    public void setDate_created(long date_created) {
        this.date_created = date_created;
    }

    public String toString() {
        return "TaskEntry: id=" + id + ", title=" + title + ", description=" + description;
    }

}
