package com.ourmail.message.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MailDO {
    @Id
    @GeneratedValue
    var id:Long? = null
    var fromUserId = 0L
    var createtime = 0L
    var title = ""
    var content = ""

    var replyToId = 0L
}