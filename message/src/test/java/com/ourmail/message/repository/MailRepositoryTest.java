package com.ourmail.message.repository;

import com.ourmail.message.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MailRepositoryTest  {
    @Autowired
    private MailRepository mailRepository;

    @Test
    public void test() {
        Mail mail = new Mail();
        mailRepository.save(mail);

    }

}