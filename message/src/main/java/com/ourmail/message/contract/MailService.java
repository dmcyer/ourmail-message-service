package com.ourmail.message.contract;

public interface MailService {
    long createMail(long backToMailId,long fromUserId, String title, String content);
    long sendMail(long backToMailId,long fromUserId, long[] receivers,long[] groupids,String[] grouppaths,String title, String content);
    Mail getMailById(long id);
}
