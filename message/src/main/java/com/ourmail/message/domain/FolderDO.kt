package com.ourmail.message.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class FolderDO {
    @Id
    @GeneratedValue
    var id:Long? = null
    var name = ""
    var type = 0// 文件夹属性
    // 用户 / 群 的 收件箱，用户的发件箱，用户/群的自定义文件夹、草稿、垃圾箱，自定义文件夹的子文件夹
    var parentId = 0L
}