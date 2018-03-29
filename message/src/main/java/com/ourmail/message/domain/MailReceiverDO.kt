package com.ourmail.message.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class MailReceiverDO {
    @Id
    @GeneratedValue
    var id:Long? = null
    var mailId = 0L
    var userId = 0L
}