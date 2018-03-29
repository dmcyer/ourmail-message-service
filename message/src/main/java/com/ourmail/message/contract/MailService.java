package com.ourmail.message.contract;

public interface MailService {
    long createMail(long fromUserId, String title, String content);
    long sendMail(long fromUserId, long[] receivers, String title, String content);
    Mail getMailById(long id);
}
