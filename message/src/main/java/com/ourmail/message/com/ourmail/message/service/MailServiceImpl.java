package com.ourmail.message.com.ourmail.message.service;

import com.ourmail.message.contract.Mail;
import com.ourmail.message.contract.MailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    @Override
    public List<Mail> getMailListByFolderId() {
        return null;
    }
}
