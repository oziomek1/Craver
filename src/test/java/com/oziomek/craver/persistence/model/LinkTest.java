package com.oziomek.craver.persistence.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkTest {

    Link link;
    Link linkDefault;
    String address = "http://www.example.com";
    String rel = "Home page";

    @BeforeEach
    void setUp() {
        link = new Link(address, rel);
        linkDefault = new Link();
    }

    @AfterEach
    void tearDown() {
        /*
        TODO remove profile from DB
         */
    }

    @Test
    @Tag("Essential")
    @DisplayName("Link getters")
    void linkGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(address, link.getLink()),
                () -> assertEquals(rel, link.getRel())
        );
    }

    @Test
    void linkDefaultGetters() {
        // given
        // when
        // then
        assertAll("default getters",
                () -> assertEquals(null, linkDefault.getLink()),
                () -> assertEquals(null, linkDefault.getRel())
        );
    }


    @Test
    void setLink() {
        // given
        // when
        link.setLink("http://www.test.com");

        // then
        assertEquals("http://www.test.com", link.getLink());
    }

    @Test
    void setRel() {
        // given
        // when
        link.setRel("Test link");

        // then
        assertEquals("Test link", link.getRel());
    }
}