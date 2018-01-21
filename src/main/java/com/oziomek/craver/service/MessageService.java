package com.oziomek.craver.service;

import com.oziomek.craver.persistence.database.DatabaseClass;
import com.oziomek.craver.persistence.model.Message;

import javax.ws.rs.NotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MessageService {
    /*
     * Further implementation will provide connection with DB
     */

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public MessageService() {
        messages.put(1L, new Message(1, new ProfileService().getProfileById(1L).getProfileName(),
                "Hello everyone. Welcome in the happy new 2018 year!",
                new GregorianCalendar(2018, 0, 1, 10, 35, 16).getTime()));
        messages.put(2L, new Message(2, new ProfileService().getProfileById(2L).getProfileName(),
                "I'm looking for a nice place to visit during winter holidays. Do you have any particular places? I want to go somewhere where I can go skiing or snowboarding.",
                new GregorianCalendar(2018, 0, 11, 15, 29, 6).getTime()));
        messages.put(3L, new Message(3, new ProfileService().getProfileById(3L).getProfileName(),
                "What's going on? I'm busy today but tomorrow you can join me on my friends birthday party. Just call me 111-111-111 or send pm",
                new GregorianCalendar(2018, 0, 18, 19, 13, 41).getTime()));
        messages.put(4L, new Message(4, new ProfileService().getProfileById(2L).getProfileName(),
                "What's up? Everyone is just learning for session? Hope you'll find some time to meet next weekend",
                new GregorianCalendar(2018, 0, 19, 10, 7, 56).getTime()));
        messages.put(5L, new Message(5, new ProfileService().getProfileById(3L).getProfileName(),
                "Have you seen last episode of Game of Thrones? What an utterly stupid ending!",
                new GregorianCalendar(2018, 0, 20, 19, 10, 1).getTime()));
        messages.put(6L, new Message(6, new ProfileService().getProfileById(3L).getProfileName(),
                "Hacked!!!!!!!!!!!",
                new GregorianCalendar(2018, 0, 21, 21, 25, 13).getTime()));
        messages.put(7L, new Message(7, new ProfileService().getProfileById(2L).getProfileName(),
                "SURPRISE",
                new GregorianCalendar(2018, 0, 21, 23, 43, 50).getTime()));
        messages.put(8L, new Message(8, new ProfileService().getProfileById(2L).getProfileName(),
                "Have a great week guys. Last one before exams. It's going to be tough. Fingers crossed!",
                new GregorianCalendar(2018, 0, 22, 1, 17, 23).getTime()));
        messages.put(9L, new Message(9, new ProfileService().getProfileById(3L).getProfileName(),
                "IMPORTANT!!! Anyone knows about the project for today???",
                new GregorianCalendar(2018, 0, 22, 9, 21, 54).getTime()));
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
        if (message.getId() < 0) {
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

    public void increaseCommentCounter(long id) {
        long counter = messages.get(id).getCommentsCounter();
        messages.get(id).setCommentsCounter(++counter);
    }

    public void decreaseCommentCounter(long id) {
        long counter = messages.get(id).getCommentsCounter();
        messages.get(id).setCommentsCounter(--counter);
    }
}
