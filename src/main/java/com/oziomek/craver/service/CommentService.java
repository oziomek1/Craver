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

    public List<Comment> getAllComments() {
        return new ArrayList<Comment>(comments.values());
    }

    public List<Comment> getCommentsForMessage(long messageId) {
        Message message = messages.get(messageId);
        if (message == null) {
            throw new NotFoundException("Message " + messageId + " not found");
        }
        List<Comment> listOfCommentsForMessage = new ArrayList<>();
        for(Comment comment : comments.values()) {
            if (comment.getMessageId() == messageId) {
                listOfCommentsForMessage.add(comment);
            }
        }
        return listOfCommentsForMessage;
    }

    public Comment getCommentById(long commentId) {
        Comment comment = comments.get(commentId);
        if (comment == null) {
            throw new NotFoundException("Comment with id " + commentId + " not found");
        }
        return comment;
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
        if (comment.getId() < 0) {
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
