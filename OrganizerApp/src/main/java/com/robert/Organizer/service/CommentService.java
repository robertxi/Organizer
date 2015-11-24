package com.robert.Organizer.service;

import com.robert.Organizer.model.Comment;
import com.robert.Organizer.model.TaskItem;

import java.util.List;

/**
 * Created by RobertXi on 11/24/15.
 */
public class CommentService {

    public static List<Comment> getCommentList(TaskItem item){
        List<Comment> ret = OrganizerDAO.getCommentList(item.getId());
        return ret;
    }

    public static List<Comment> addNewComment(Comment comment){
        OrganizerDAO.addNewComment(comment);
        List<Comment> ret = OrganizerDAO.getCommentList(comment.getTaskitem_id());
        return ret;
    }
}
