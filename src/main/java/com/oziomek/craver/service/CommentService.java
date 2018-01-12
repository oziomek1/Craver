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

    private Map<Long, Comment> comments = DatabaseClass.getComments();
    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public CommentService() {
        comments.put(1L, new Comment(1, new MessageService().getMessage(1).getId(),
                new ProfileService().getProfileById(1L).getProfileName(), "Hello"));
        comments.put(2L, new Comment(2, new MessageService().getMessage(1).getId(),
                new ProfileService().getProfileById(1L).getProfileName(), "Hello again"));
    }

    public List<Comment> getCommentsForMessage(long messageId) {
        Message message = messages.get(messageId);
        if (message == null) {
            throw new NotFoundException("Message " + messageId + " not found");
        }
        List<Comment> listOfComments = new ArrayList<>();
        for(Map.Entry<Long, Comment> comment : comments.entrySet()) {
            if (comment.getValue().getMessageId() == messageId) {
                listOfComments.add(comment.getValue());
            }
        }
        return listOfComments;
    }

    public Comment getComment(long messageId, long commentId) {
        Message newMessage = messages.get(messageId);
        if (newMessage == null) {
            throw new NotFoundException("Message " + messageId + " not found");
        }
        Comment newComment = comments.get(commentId);
        if (newComment == null) {
            throw new NotFoundException("Comment with id " + commentId + " not found");
        }
        List<Comment> listOfComments = new ArrayList<>();
        for(Map.Entry<Long, Comment> comment : comments.entrySet()) {
            if (comment.getValue().getMessageId() == messageId) {
                listOfComments.add(comment.getValue());
            }
        }
        for (Comment comment: listOfComments) {
            if (comment.getId() == commentId) {
                return comment;
            }
        }
        throw new NotFoundException("Comment for message " + messageId + " with id " + commentId + "not found");
    }

    public Comment addComment(long messageId, Comment comment) {
        comment.setId(comments.size() + 1);
        comment.setMessageId(messageId);
        if (comment.getDate() == null) {
            comment.setDate(new Date());
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(Comment comment) {
        if (comment.getId() <= 0) {
            return null;
        }
        if (comment.getDate() == null) {
            comment.setDate(new Date());
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long commentId) {
        return comments.remove(commentId);
    }
}
