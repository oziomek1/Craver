package com.oziomek.craver.persistence.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name="profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class Profile {
    @XmlAttribute
    private long id;
    @XmlElement(required=true)
    private String profileName;
    @XmlElement(required=true)
    private String firstName;
    @XmlElement(required=true)
    private String lastName;
    @XmlElement
    private Date dateCreated;

    public Profile() {
    }

    public Profile(long id, String profileName, String firstName, String lastName) {
        this.id = id;
        this.profileName = profileName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateCreated = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
