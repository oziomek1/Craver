package com.oziomek.craver.persistence.model;

import com.oziomek.craver.auth.UserRole;

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
    private String password;
    @XmlElement
    private static String Role;
    public Profile() {
    }

    public Profile(long id, String profileName, String firstName, String lastName, String password) {
        this.id = id;
        this.profileName = profileName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateCreated = new Date();
        this.password = password;
        this.Role = UserRole.USER_ROLE;
    }

    public Profile(long id, String profileName, String firstName, String lastName, String password, String Role) {
        this.id = id;
        this.profileName = profileName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateCreated = new Date();
        this.password = password;
        this.Role = Role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRoleToAdmin(String Role) {
        Role = UserRole.ADMIN_ROLE;
        this.Role = Role;
    }

    public void setRoleToUser(String Role) {
        Role = UserRole.USER_ROLE;
        this.Role = Role;
    }

}
