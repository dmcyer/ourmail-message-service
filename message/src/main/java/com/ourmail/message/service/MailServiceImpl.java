package com.ourmail.message.service;

import com.ourmail.message.contract.Mail;
import com.ourmail.message.contract.MailService;
import com.ourmail.message.domain.*;
import com.ourmail.message.repository.*;
import com.ourmail.user.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MailServiceImpl implements MailService {
    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private MailReceiverRepository mailReceiverRepository;
    @Autowired
    private MailGroupReceiverRepository mailGroupReceiverRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private MailFolderRepository mailFolderRepository;

    @Override
    public long createMail(long fromUserId, String title, String content) {
        MailDO mailDO = new MailDO();
        mailDO.setFromUserId(fromUserId);
        mailDO.setTitle(title);
        mailDO.setContent(content);
        mailDO.setCreatetime(System.currentTimeMillis());
        mailRepository.save(mailDO);
        return mailDO.getId();
    }
    //mailDO.setReplyToId(backToMai);
    //the following code was copied from the latter
    @Override
    public void sendToGroup(long mailId, long[] userIds) {
        for (long userid : userIds) {
            MailGroupReceiverDO mailReceiverDO = new MailGroupReceiverDO();
            mailReceiverDO.setMailId(mailId);
            mailReceiverDO.setGroupId(userid);
            mailGroupReceiverRepository.save(mailReceiverDO);
        }
    }

    @Override
    public void replyTo(long replyToId, long mailId) {
        MailDO mailDO = mailRepository.findById(mailId).get();//？？？这里findbyid是不是用错了  没有 这里的核心关键字id就是mailid
        mailDO.setReplyToId(replyToId);
        mailRepository.save(mailDO);//update
    }

    @Override
    public List<Mail> getMailsByFolder(long folderId) {
        Iterable<MailFolderDO> mailFolderDOS = mailFolderRepository.findAllByFolderId(folderId);
        List<Mail> mails = new ArrayList<>();
        for (MailFolderDO mailFolderDO : mailFolderDOS) {
            mails.add(getMailById(  mailFolderDO.getMailId()));
        }
        return mails;
    }

    @Override
    public void addFolder(long mailId, long folderId) {
        //???FolderDO folderDO = folderRepository.findById(mailId).get();

        MailFolderDO mailFolderDO = new MailFolderDO();
        mailFolderDO.setFolderId(folderId);
        mailFolderDO.setMailId(mailId);
        mailFolderRepository.save(mailFolderDO);
    }

    @Override
    public void removeFolder(long Id, long folderId) {
        Iterable<MailFolderDO> mailFolderDOS = mailFolderRepository.findAllByFolderId(folderId);
        for (MailFolderDO mailFolderDO : mailFolderDOS) {
            if(mailFolderDO.getMailId()==Id){
                mailFolderRepository.deleteByMailId(mailFolderDO.getId());
            }
        }
        //        mailFolderRepository.delete();
    }

    @Override
    public void sendToUser(long mailId, long[] userIds) {
        for (long userid : userIds) {
            MailReceiverDO mailReceiverDO = new MailReceiverDO();
            mailReceiverDO.setMailId(mailId);
            mailReceiverDO.setUserId(userid);
            mailReceiverRepository.save(mailReceiverDO);
        }
    }

    @Override
    public Mail getMailById(long id) {
        MailDO mailDO = mailRepository.findById(id).get();
        Mail mail = new Mail();
        mail.setId(mailDO.getId());
        mail.setTitle(mailDO.getTitle());
        mail.setContent(mailDO.getContent());
        long fromUserId = mailDO.getFromUserId();
        mail.setFromUserId(fromUserId);
        mail.setReplyToId(mailDO.getReplyToId());//mail.setBackToMailId(mailDO.getBackToMailId());
        Iterable<MailReceiverDO> mailReceiverDOS = mailReceiverRepository.findAllByMailId(id);
        Iterable<MailGroupReceiverDO> mailGroupReceiverDOS = mailGroupReceiverRepository.findAllByMailId(id);
        List<Long> list = new ArrayList<>();


        for (MailReceiverDO mailReceiverDO : mailReceiverDOS) {
            list.add(mailReceiverDO.getUserId());
            //System.out.println(mailReceiverDO.getUserId());
            //mail.getReceiverIds().add(mailReceiverDO.getUserId());//？？？不指针也能这样写？
        }
        mail.setReceiverIds(list);
        list = new ArrayList<>();
        for (MailGroupReceiverDO mailGroupReceiverDO : mailGroupReceiverDOS) {
            list.add(mailGroupReceiverDO.getGroupId());

            //mail.getGroupIds().add(mailGroupReceiverDO.getGroupId());
            //mail.getGroupPaths().add(mailGroupReceiverDO.getPath());
        }
        mail.setGroupIds(list);
        return mail;
    }
}
