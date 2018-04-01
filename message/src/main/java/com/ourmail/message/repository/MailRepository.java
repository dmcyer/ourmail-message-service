package com.ourmail.message.repository;

import com.ourmail.message.domain.MailDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Service
@Controller
@Repository
public interface MailRepository extends CrudRepository<MailDO, Long> {

}
