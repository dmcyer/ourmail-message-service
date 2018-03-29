package com.ourmail.message.service;

import com.ourmail.message.contract.Mail;
import com.ourmail.message.contract.MailService;
import com.ourmail.message.domain.MailDO;
import com.ourmail.message.domain.MailReceiverDO;
import com.ourmail.message.repository.MailReceiverRepository;
import com.ourmail.message.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MailServiceImpl implements MailService {
    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private MailReceiverRepository mailReceiverRepository;

    //这里是用户函数的编写，材料是底层函数（更底层和刚设的）
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

    @Override
    public long sendMail(long fromUserId, long[] receivers, String title, String content) {
        long mailId = createMail(fromUserId, title, content);
        for (long receiverId : receivers) {
            MailReceiverDO mailReceiverDO = new MailReceiverDO();
            mailReceiverDO.setMailId(mailId);
            mailReceiverDO.setUserId(receiverId);
            mailReceiverRepository.save(mailReceiverDO);
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
        mail.setFromUserId(mailDO.getFromUserId());
        Iterable<MailReceiverDO> mailReceiverDOS = mailReceiverRepository.findAllByMailId(id);
        for (MailReceiverDO mailReceiverDO : mailReceiverDOS) {
            mail.getReceiverIds().add(mailReceiverDO.getId());
        }
        return mail;
    }
}
