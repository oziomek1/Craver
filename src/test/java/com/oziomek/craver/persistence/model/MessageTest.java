package com.oziomek.craver.persistence.model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageTest {

    Message message;
    Message messageDefault;
    Message messageContent;
    Message messageDate;
    Message messageLinks;
    Long id = 1L;
    String author = "Author";
    String content = "example content";
    Date date;
    Calendar cal = Calendar.getInstance();
    List<Link> links = new ArrayList<>();


    @BeforeEach
    void setUp() {
        cal.setTimeInMillis(0);
        cal.set(2018, 0, 1, 12, 00, 00);
        date = cal.getTime();
        String url = "http://www.example.com";
        Link link = new Link(url, "example link");
        links.add(link);
        message = new Message(id, author, content, date, links);
        messageDefault = new Message();
        messageContent = new Message(id, author, content);
        messageDate = new Message(id, author, content, date);
        messageLinks = new Message(id, author, content, links);
    }

    @AfterEach
    void tearDown() {
        /*
        TODO remove message from DB
         */
    }

    @Test
    @DisplayName("Message getters")
    void messageGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(1L, message.getId()),
                () -> assertEquals(author, message.getAuthor()),
                () -> assertEquals(content, message.getContent()),
                () -> assertEquals(date, message.getDate()),
                () -> assertEquals(0L, message.getCommentsCounter()),
                () -> assertEquals(links, message.getLinks())
        );
    }

    @Test
    void setMessageDefaultGetters() {
        // given
        // when
        // then
        assertAll("default getters",
                () -> assertEquals(0L, messageDefault.getId()),
                () -> assertEquals(null, messageDefault.getAuthor()),
                () -> assertEquals(null, messageDefault.getContent())
        );
    }

    @Test
    void messageContentGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(1L, messageContent.getId()),
                () -> assertEquals(author, messageContent.getAuthor()),
                () -> assertEquals(content, messageContent.getContent())
        );
    }

    @Test
    void messageDateGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(1L, messageDate.getId()),
                () -> assertEquals(author, messageDate.getAuthor()),
                () -> assertEquals(content, messageDate.getContent()),
                () -> assertEquals(date, messageDate.getDate())
        );
    }

    @Test
    void messageLinksGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(1L, messageLinks.getId()),
                () -> assertEquals(author, messageLinks.getAuthor()),
                () -> assertEquals(content, messageLinks.getContent()),
                () -> assertEquals(links, messageLinks.getLinks())
        );
    }

    @Test
    void setId() {
        // given
        // when
        message.setId(2L);

        //then
        assertEquals(2L, message.getId());
    }

    @Test
    void setAuthor() {
        // given
        // when
        message.setAuthor("Bob");

        //then
        assertEquals("Bob", message.getAuthor());
    }

    @Test
    void setContent() {
        // given
        // when
        message.setContent("New content");

        //then
        assertEquals("New content", message.getContent());
    }

    @Test
    void setDate() {
        // given
        cal.setTimeInMillis(0);
        cal.set(2017, 11, 30, 8, 30, 30);
        Date date2 = cal.getTime();

        // when
        message.setDate(date2);

        //then
        assertEquals(date2, message.getDate());
    }

    @Test
    void setCommentsCounter() {
        //given
        //when
        message.setCommentsCounter(10L);

        //then
        assertEquals(10L, message.getCommentsCounter());
    }

    @Test
    void setLinks() {
        //given
        String url2 = "http://www.random.com";
        Link link2 = new Link(url2, "random link");
        List<Link> links2 = new ArrayList<>();

        //when
        message.setLinks(links2);

        //then
        assertEquals(links2, message.getLinks());
    }

    @Test
    void addLink() {
        //given
        String url1 = "http://www.link.com";
        String url2 = "http://www.random.com";
        Link link2 = new Link(url2, "random link");
        List<Link> links2 = new ArrayList<>();
        links2.add(link2);
        message.setLinks(links2);
        //when
        message.addLink(url1, "link");

        //then
        assertEquals(links2, message.getLinks());
    }

    @Test
    void removeLinks() {
        //given
        String url2 = "http://www.random.com";
        Link link2 = new Link(url2, "random link");
        List<Link> links2 = new ArrayList<>();
        links2.add(link2);
        message.setLinks(links2);

        List<Link> empty_list = new ArrayList<>();

        //when
        message.removeLinks();

        //then
        assertEquals(empty_list, message.getLinks());
    }

}