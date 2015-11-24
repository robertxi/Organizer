package com.robert.solr;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by RobertXi on 10/5/15.
 */
public class Task {

    @Field
    private String id;

    @Field
    private String title;

    @Field
    private String description;




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


    public String toString() {
        return ("Task [id=" + id + ", title=" + title + ", description=" + description + ", taskNum=" + id + "]");
    }

}
