package com.ourmail.message.contract;

import com.ourmail.message.repository.MailRepository;
import com.ourmail.user.contract.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    public static final String TEST_TITLE = "test title";
    public static final String TEST_CONTENT = "test content";
    public static final String TEST_USER_NAME = "ourmail";
    @Autowired
    private MailService mailService;

    @Autowired
    private MailRepository mailRepository;

    @MockBean
    private UserService userService;

    @Test
    public void createMail() {
        when(userService.getNameById(1)).thenReturn(TEST_USER_NAME);
        long mailId = mailService.createMail(1,1, TEST_TITLE, TEST_CONTENT);
        Mail mail = mailService.getMailById(mailId);
        assertEquals(mail.getTitle(), TEST_TITLE);
        assertEquals(mail.getContent(), TEST_CONTENT);
    }
    @Test
    public void sendMail() {
        when(userService.getNameById(1)).thenReturn(TEST_USER_NAME);
        long mailId = mailService.sendMail(23,1, new long[] {2,4,5},new long[]{3,15},new String[]{"1223","123"}, TEST_TITLE, TEST_CONTENT);
        Mail mail = mailService.getMailById(mailId);
        assertEquals((long)mail.getBackToMailId(), 23L);
        assertEquals((long)mail.getGroupIds().get(0), 3L);
        assertEquals(mail.getGroupPaths().get(0), "1223");
        assertEquals(mail.getTitle(), TEST_TITLE);
        assertEquals(mail.getContent(), TEST_CONTENT);
        assertEquals(mail.getFromUserId(), 1);
        assertEquals(mail.getFromUserName(), TEST_USER_NAME);
        assertEquals(mail.getReceiverIds().size(), 3);
        assertEquals((long)mail.getReceiverIds().get(0), 2L);
        assertEquals((long)mail.getReceiverIds().get(1), 4L);
        assertEquals((long)mail.getReceiverIds().get(2), 5L);
    }
}