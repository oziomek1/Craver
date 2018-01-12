package com.oziomek.craver.service;

import com.oziomek.craver.persistence.database.DatabaseClass;
import com.oziomek.craver.persistence.model.Message;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MessageService {
    /*
     * Further implementation will provide connection with DB
     */

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1, new ProfileService().getProfileById(1L).getProfileName(), "Hello"));
        messages.put(2L, new Message(2, new ProfileService().getProfileById(2L).getProfileName(), "Hello again"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public Message getMessage(long id) {
        Message message = messages.get(id);
        if (message == null) {
            throw new NotFoundException("Message with id " + id + " not found");
        }
        return message;
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        if (message.getDate() == null) {
            message.setDate(new Date());
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        if (message.getDate() == null) {
            message.setDate(new Date());
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }
}
