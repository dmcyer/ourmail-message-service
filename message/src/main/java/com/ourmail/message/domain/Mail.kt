package com.ourmail.message.domain

import javax.persistence.Entity

@Entity
class Mail {
    var id = 0L
    var title = ""
    var content = ""
}