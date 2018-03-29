package com.ourmail.message.contract

class Mail {
    var id = 0L
    var title = ""
    var content = ""
    var fromUserId = 0L
    var receiverIds = mutableListOf<Long>()
}