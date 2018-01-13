package com.oziomek.craver.service;

import com.oziomek.craver.persistence.database.DatabaseClass;
import com.oziomek.craver.persistence.model.Comment;
import com.oziomek.craver.persistence.model.Message;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();


    public List<Comment> getCommentsForMessage(long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        List<Comment> listOfComments = new ArrayList<>(comments.values());
        return listOfComments;
    }

    public Comment getCommentById(long messageId, long commentId) {
        Message message = messages.get(messageId);
        if (message == null) {
            throw new NotFoundException("Message with id " + messageId + " not found");
        }
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
        if (comment == null) {
            throw new NotFoundException("Comment with id " + commentId + " not found");
        }
        return comments.get(commentId);
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        if (comment.getDate() == null) {
            comment.setDate(new Date());
        }
        messages.get(messageId).getComments().put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() < 0 || messageId < 0) {
            return null;
        }
        if (comment.getDate() == null) {
            comment.setDate(new Date());
        }
        messages.get(messageId).getComments().put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }

}
