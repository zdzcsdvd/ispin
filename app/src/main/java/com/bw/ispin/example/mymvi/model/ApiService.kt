package com.bw.ispin.example.mymvi.model

import com.bw.ispin.example.mymvi.entity.ResponseData
import com.bw.ispin.example.mymvi.entity.VideoData
import com.example.repofitutils.entity.VideoTypeEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/videosimple/getSimpleVideoByChannelId")
    suspend fun getSimpleVideoByChannelId(@Query("channelId")channelId:String,
                                             @Query("page")page:Int,
                                             @Query("pagesize")pagesize:Int): ResponseData<List<VideoData>>
    @GET("/videotype/getSimpleType")
    suspend fun getSimpleType():ResponseData<List<VideoTypeEntity>>
}