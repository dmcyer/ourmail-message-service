package com.ourmail.message.repository;

import com.ourmail.message.domain.MailDO;
import com.ourmail.message.domain.MailFolderDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Service
@Controller
@Repository
public interface MailFolderRepository extends CrudRepository<MailFolderDO, Long> {
    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    Iterable<MailFolderDO> findAllByFolderId(long folderId);
    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void deleteByMailId(long mailId);
}
