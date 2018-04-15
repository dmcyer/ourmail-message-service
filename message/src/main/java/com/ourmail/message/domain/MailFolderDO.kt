package com.ourmail.message.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MailFolderDO {
    @Id
    @GeneratedValue
    var id:Long? = null
    var folderId = 0L
    var mailId = 0L
    // 这个数据结构的含义是：有一个邮件的目录包含folderid（单独）
}