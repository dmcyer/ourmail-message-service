package com.ourmail.message.contract

class Mail {
    var id = 0L
    var title = ""
    var content = ""
    var fromUserId = 0L
    var backToMailId = 0L
    var receiverIds = mutableListOf<Long>()
    var groupIds = mutableListOf<Long>()
    var groupPaths = mutableListOf<String>()
    //需别人子程序或mock的变量
    var fromUserName = ""
    //var backToUserName = ""
}