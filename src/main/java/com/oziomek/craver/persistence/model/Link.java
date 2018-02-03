package com.oziomek.craver.persistence.model;

public class Link {

    public Link() {

    }

    public Link(String link, String rel) {
        this.link = link;
        this.rel = rel;
    }

    private String link;
    private String rel;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
