package com.ourmail.message.repository;

import com.ourmail.message.domain.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {
}
