package com.oziomek.craver.persistence.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name="comment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {
    @XmlAttribute
    private long id;
    @XmlElement(required=true)
    private long messageId;
    @XmlElement(required=true)
    private String author;
    @XmlElement(required=true)
    private String content;
    @XmlElement
    private Date date;

    public Comment() {
        /*
         * Necessary for conversion with XML/JSON
         */
    }

    public Comment(long id, long messageId, String author, String content) {
        this.id = id;
        this.messageId = messageId;
        this.author = author;
        this.content = content;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
