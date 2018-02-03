package com.oziomek.craver.persistence.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    Profile profile;
    Profile profileDefault;
    Profile profileNoRole;
    Long id = 1L;
    String profileName = "Tedro";
    String firstName = "Ted";
    String lastName = "Rowing";
    String password = "zaq12wsx";
    String role = "GOD";

    @BeforeEach
    void setUp() {
        profileDefault = new Profile();
        profile = new Profile(id, profileName, firstName, lastName, password, role);
        profileNoRole = new Profile(id, profileName, firstName, lastName, password);
    }

    @AfterEach
    void tearDown() {
        /*
        TODO remove profile from DB
         */
    }

    @Test
    @DisplayName("Profile getters")
    void profileGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(1L, profile.getId()),
                () -> assertEquals(profileName, profile.getProfileName()),
                () -> assertEquals(firstName, profile.getFirstName()),
                () -> assertEquals(lastName, profile.getLastName()),
                () -> assertEquals(password, profile.getPassword()),
                () -> assertEquals(role, profile.getRole())
        );
    }

    @Test
    void profileNoRoleGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(1L, profileNoRole.getId()),
                () -> assertEquals(profileName, profileNoRole.getProfileName()),
                () -> assertEquals(firstName, profileNoRole.getFirstName()),
                () -> assertEquals(lastName, profileNoRole.getLastName()),
                () -> assertEquals(password, profileNoRole.getPassword()),
                () -> assertEquals("USER", profileNoRole.getRole())
        );
    }

    @Test
    void profileDefaultGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(0L, profileDefault.getId()),
                () -> assertEquals(null, profileDefault.getProfileName()),
                () -> assertEquals(null, profileDefault.getFirstName()),
                () -> assertEquals(null, profileDefault.getLastName()),
                () -> assertEquals(null, profileDefault.getPassword())
        );
    }

    @Test
    void setId() {
        // given
        // when
        profile.setId(2L);

        //then
        assertEquals(2L, profile.getId());
    }

    @Test
    void setProfileName() {
        // given
        // when
        profile.setProfileName("J4n00sh");

        //then
        assertEquals("J4n00sh", profile.getProfileName());
    }

    @Test
    void setFirstName() {
        // given
        // when
        profile.setFirstName("Sebix");

        //then
        assertEquals("Sebix", profile.getFirstName());
    }

    @Test
    void setLastName() {
        // given
        // when
        profile.setLastName("Karynka");

        //then
        assertEquals("Karynka", profile.getLastName());
    }

    @Test
    void setDateCreated() {
        //given
        Date date = new Date();

        // when
        profile.setDateCreated(date);

        // then
        assertEquals(date, profile.getDateCreated());
    }

    @Test
    void setPassword() {
        // given
        String pass = "A1b2C3d4E5";
        // when
        profile.setPassword(pass);

        // then
        assertEquals(pass, profile.getPassword());
    }

    @Test
    void setRoleToAdmin() {
        // given
        // when
        profile.setRoleToAdmin();

        // then
        assertEquals("ADMIN", profile.getRole());
    }

    @Test
    void setRoleToUser() {
        // given
        // when
        profile.setRoleToUser();

        // then
        assertEquals("USER", profile.getRole());
    }
}
