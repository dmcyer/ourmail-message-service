package com.ourmail.message.contract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    public static final String TEST_TITLE = "test title";
    public static final String TEST_CONTENT = "test content";
    @Autowired
    private MailService mailService;

    @Test
    public void createMail() {
        long mailId = mailService.createMail(1, TEST_TITLE, TEST_CONTENT);
        Mail mail = mailService.getMailById(mailId);
        assertEquals(mail.getTitle(), TEST_TITLE);
        assertEquals(mail.getContent(), TEST_CONTENT);
    }
    @Test
    public void sendMail() {
        long mailId = mailService.sendMail(1, new long[] {2,3,4}, TEST_TITLE, TEST_CONTENT);
        Mail mail = mailService.getMailById(mailId);
        assertEquals(mail.getTitle(), TEST_TITLE);
        assertEquals(mail.getContent(), TEST_CONTENT);
        assertEquals(mail.getFromUserId(), 1);
        assertEquals(mail.getReceiverIds().size(), 3);
        assertEquals((long)mail.getReceiverIds().get(0), 2L);
        assertEquals((long)mail.getReceiverIds().get(1), 3L);
        assertEquals((long)mail.getReceiverIds().get(2), 4L);
    }
}