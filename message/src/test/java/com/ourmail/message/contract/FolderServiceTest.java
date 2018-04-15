package com.ourmail.message.contract;

import com.ourmail.message.repository.*;
import com.ourmail.user.contract.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FolderServiceTest {
    public static final String TEST_TITLE = "test title";
    public static final String TEST_CONTENT = "test content";
    public static final String TEST_USER_NAME = "ourmail";
    @Autowired
    private MailService mailService;
@Autowired
    private FolderService folderService;
    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private MailReceiverRepository mailReceiverRepository;
    @Autowired
    private MailGroupReceiverRepository mailGroupReceiverRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private MailFolderRepository mailFolderRepository;
    @MockBean
    private UserService userService;


    @Test
    public void box() {
        //when(userService.getNameById(2)).thenReturn(TEST_USER_NAME);
        //when(userService.getNameById(1)).thenReturn(TEST_USER_NAME);
        long inboxId = folderService.createUserInbox(1);
        long inboxId1 = folderService.getUserInbox(1);
        assertEquals(inboxId, inboxId1);
        long outboxId = folderService.createUserOutbox(1);
        assertEquals(outboxId, folderService.getUserOutbox(1));
        long userboxId = folderService.createUserFolder(1, "123", 1);//parentid=userid
        List<Folder> a = folderService.getUserFolderList(1);
        System.out.println(a.get(0).getName());
        System.out.println(a.get(0).getType());

        System.out.println(a.get(1).getName());//群类似

    }

    @Test
    public void boxGroup() {
        //when(userService.getNameById(2)).thenReturn(TEST_USER_NAME);
        //when(userService.getNameById(1)).thenReturn(TEST_USER_NAME);
        long inboxId = folderService.createGroupInbox(1);
        long inboxId1 = folderService.getGroupInbox(1);
        assertEquals(inboxId, inboxId1);
        long userboxId = folderService.createGroupFolder(1, "123", 1);//parentid=userid
        List<Folder> a = folderService.getGroupFolderList(1);
        System.out.println(a.get(0).getName());
        System.out.println(a.get(0).getType());

        System.out.println(a.get(1).getName());//群类似

    }


}