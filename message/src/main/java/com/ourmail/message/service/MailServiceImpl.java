package com.ourmail.message.service;

import com.ourmail.message.contract.Mail;
import com.ourmail.message.contract.MailService;
import com.ourmail.message.domain.MailDO;
import com.ourmail.message.domain.MailGroupReceiverDO;
import com.ourmail.message.domain.MailReceiverDO;
import com.ourmail.message.repository.MailReceiverRepository;
import com.ourmail.message.repository.MailRepository;
import com.ourmail.user.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ourmail.message.repository.MailGroupReceiverRepository;

import javax.transaction.Transactional;

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

    @Override
    public long createMail(long backToMailId,long fromUserId, String title, String content) {
        //初始化
        MailDO mailDO = new MailDO();
        mailDO.setFromUserId(fromUserId);
        mailDO.setTitle(title);
        mailDO.setContent(content);
        mailDO.setCreatetime(System.currentTimeMillis());
        mailDO.setBackToMailId(backToMailId);
        mailRepository.save(mailDO);
        return mailDO.getId();
    }

    @Override
    public long sendMail(long backToMailId,long fromUserId, long[] receivers,long[] groupids,String[] grouppaths, String title, String content) {
        long mailId = createMail(backToMailId,fromUserId, title, content);
        for (long receiverId : receivers) {
            MailReceiverDO mailReceiverDO = new MailReceiverDO();
            mailReceiverDO.setMailId(mailId);
            mailReceiverDO.setUserId(receiverId);
            mailReceiverRepository.save(mailReceiverDO);
        }
        for(int j=0;j<groupids.length;j++){
            MailGroupReceiverDO mailGroupReceiverDO = new MailGroupReceiverDO();
            mailGroupReceiverDO.setMailId(mailId);
            mailGroupReceiverDO.setGroupId(groupids[j]);
            mailGroupReceiverDO.setPath(grouppaths[j]);
            mailGroupReceiverRepository.save(mailGroupReceiverDO);
        }
        return mailId;
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
        mail.setFromUserName(userService.getNameById(fromUserId));
        mail.setBackToMailId(mailDO.getBackToMailId());
        Iterable<MailReceiverDO> mailReceiverDOS = mailReceiverRepository.findAllByMailId(id);
        Iterable<MailGroupReceiverDO> mailGroupReceiverDOS = mailGroupReceiverRepository.findAllByMailId(id);
        for (MailReceiverDO mailReceiverDO : mailReceiverDOS) {
            mail.getReceiverIds().add(mailReceiverDO.getUserId());
        }
        for (MailGroupReceiverDO mailGroupReceiverDO : mailGroupReceiverDOS) {
            mail.getGroupIds().add(mailGroupReceiverDO.getGroupId());
            mail.getGroupPaths().add(mailGroupReceiverDO.getPath());
        }
        return mail;
    }
}
