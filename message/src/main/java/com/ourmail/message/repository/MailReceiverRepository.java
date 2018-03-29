package com.ourmail.message.repository;

import com.ourmail.message.domain.MailDO;
import com.ourmail.message.domain.MailReceiverDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailReceiverRepository extends CrudRepository<MailReceiverDO, Long> {

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    Iterable<MailReceiverDO> findAllByMailId(long mailId);
}
