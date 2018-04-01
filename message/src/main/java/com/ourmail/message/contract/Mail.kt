package com.ourmail.message.contract

class Mail {
    var id = 0L
    var title = ""
    var content = ""
    var fromUserId = 0L
    var fromUserName = ""
    var receiverIds = mutableListOf<Long>()
    var groupIds = mutableListOf<Long>()
    var groupPaths = mutableListOf<String>()
    var backToMailId = 0L

}