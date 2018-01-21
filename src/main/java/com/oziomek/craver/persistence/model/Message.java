package com.oziomek.craver.persistence.model;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name="message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @XmlAttribute
	private long id;
    @XmlElement(required=true)
	private String author;
    @XmlElement(required=true)
	private String content;
    @XmlElement
	private Date date;
    @XmlElement(name = "links")
	private List<Link> links = new ArrayList<>();
    @XmlAttribute
    private long commentsCounter;
    @XmlElement
    private Map<Long, Comment> comments = new HashMap<>();

    public Message() {
        /*
         * Necessary for conversion with XML/JSON
         */
    }

    public Message(long id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = new Date();
    }

    public Message(long id, String author, String content, Date date) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public Message(long id, String author, String content, List<Link> links) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.links = links;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }

    public long getCommentsCounter() {
        return commentsCounter;
    }

    public void setCommentsCounter(long commentsCounter) {
        this.commentsCounter = commentsCounter;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String url, String rel) {
        Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    public void removeLinks() {
        links.clear();
    }
}
