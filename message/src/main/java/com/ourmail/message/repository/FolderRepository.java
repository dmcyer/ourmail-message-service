package com.ourmail.message.repository;

import com.ourmail.message.domain.FolderDO;
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
public interface FolderRepository extends CrudRepository<FolderDO, Long> {
    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    Iterable<FolderDO> findAllByParentId(long parentId);

}