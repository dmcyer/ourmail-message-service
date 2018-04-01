package com.ourmail.message.repository;

import com.ourmail.message.domain.MailGroupReceiverDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailGroupReceiverRepository extends CrudRepository<MailGroupReceiverDO, Long> {

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    Iterable<MailGroupReceiverDO> findAllByMailId(long mailId);
}
