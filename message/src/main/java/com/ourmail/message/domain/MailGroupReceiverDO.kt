package com.ourmail.message.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MailGroupReceiverDO {
    @Id
    @GeneratedValue
    var id:Long? = null
    var mailId = 0L
    var groupId = 0L
    //receiver 也这样做？
}