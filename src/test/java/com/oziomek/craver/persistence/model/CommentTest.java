package com.oziomek.craver.persistence.model;

import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentTest {

    Comment comment;
    Comment commentDefault;
    Comment commentDate;
    Long id = 1L;
    Long messageId = 1L;
    String author = "Ryan";
    String content = "Text of a comment";
    Date date;
    Calendar cal = Calendar.getInstance();

    @BeforeEach
    void setUp() {
        cal.setTimeInMillis(0);
        cal.set(2018, 1, 15, 3, 25, 45);
        date = cal.getTime();
        comment = new Comment(id, messageId, author, content);
        commentDefault = new Comment();
        commentDate = new Comment(id, messageId, author, content, date);
    }

    @AfterEach
    void tearDown() {
         /*
        TODO remove message from DB
         */
    }

    @Test
    @Tag("Essential")
    @DisplayName("Comment getters")
    void commentGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(1L, comment.getId()),
                () -> assertEquals(1L, comment.getMessageId()),
                () -> assertEquals(author, comment.getAuthor()),
                () -> assertEquals(content, comment.getContent())
        );
    }

    @Test
    void commentDefaultGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(0L, commentDefault.getId()),
                () -> assertEquals(0L, commentDefault.getMessageId()),
                () -> assertEquals(null, commentDefault.getAuthor()),
                () -> assertEquals(null, commentDefault.getContent())
        );
    }

    @Test
    void commentDateGetters() {
        // given
        // when
        // then
        assertAll("getters",
                () -> assertEquals(1L, commentDate.getId()),
                () -> assertEquals(1L, commentDate.getMessageId()),
                () -> assertEquals(author, commentDate.getAuthor()),
                () -> assertEquals(content, commentDate.getContent()),
                () -> assertEquals(date, commentDate.getDate())
        );
    }

    @Test
    void setId() {
        // given
        // when
        comment.setId(1932L);

        // then
        assertEquals(1932L, comment.getId());
    }

    @Test
    void setMessageId() {
        // given
        // when
        comment.setMessageId(3L);

        // then
        assertEquals(3L, comment.getMessageId());
    }

    @Test
    void setAuthor() {
        // given
        // when
        comment.setAuthor("Tonny");

        // then
        assertEquals("Tonny", comment.getAuthor());
    }

    @Test
    void setContent() {
        // given
        // when
        comment.setContent("Lorem ipsum");

        // then
        assertEquals("Lorem ipsum", comment.getContent());
    }

    @Test
    void setDate() {
        // given
        cal.setTimeInMillis(0);
        cal.set(2018, 5, 10, 8, 30, 30);
        Date dateSetter = cal.getTime();
        // when
        comment.setDate(dateSetter);

        // then
        assertEquals(dateSetter, comment.getDate());
    }
}