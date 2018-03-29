package com.ourmail.message.repository;

import com.ourmail.message.domain.MailDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends CrudRepository<MailDO, Long> {

}
