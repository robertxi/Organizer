package com.robert.Organizer.solrTests;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

/**
 * Created by RobertXi on 11/22/15.
 */
public class Book {
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    @Field("categories_txt")
    private List<Category> categories;


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "Book [id=" + id + ", description=" + description + ", title=" + name + "]";
    }



}
