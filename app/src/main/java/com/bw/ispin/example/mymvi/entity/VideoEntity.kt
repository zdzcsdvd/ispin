package com.bw.ispin.example.mymvi.entity

import java.io.Serializable

data class VideoData(
    var avatar_url: String,
    var channelid: String,
    var commentnum: Int,
    var ctime: String,
    var description: String,
    var group_id: String,
    var id: Int,
    var image_url: String,
    var item_id: String,
    var name: String,
    var playnum: Int,
    var preview_url: String,
    var publish_time: Any,
    var title: String,
    var userid: String,
    var verifycode: String,
    var videomainimag: String,
    var videopath: String
) : Serializable

data class NewsData(
    var countId: Any,
    var current: Int,
    var maxLimit: Any,
    var optimizeCountSql: Boolean,
    var orders: List<Any>,
    var pages: Int,
    var records: List<Record>,
    var searchCount: Boolean,
    var size: Int,
    var total: Int
): Serializable

data class Record(
    var content: String,
    var createTime: String,
    var flag: Int,
    var id: Int,
    var imgurl: String,
    var looks: Int,
    var ntid: Int,
    var suid: Int,
    var title: String
)