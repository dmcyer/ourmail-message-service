package com.ourmail.message.contract;

import java.util.List;

public interface MailService {
    List<Mail> getMailListByFolderId();
}
