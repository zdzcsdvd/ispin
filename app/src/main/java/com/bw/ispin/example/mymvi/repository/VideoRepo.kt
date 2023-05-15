package com.bw.ispin.example.mymvi.repository

import com.bw.ispin.example.mymvi.entity.ResponseData
import com.bw.ispin.example.mymvi.entity.VideoData
import com.bw.ispin.example.mymvi.model.ApiService
import com.example.mymvi.model.RetrofitManager
import com.example.repofitutils.entity.VideoTypeEntity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepo {
    val apiService = RetrofitManager.instance?.retrofit!!.create(ApiService::class.java)

    suspend fun getSimpleVideoByChannelId(channelId:String,page:Int,pagesize:Int):ResponseData<List<VideoData>>{
        return  withContext(Dispatchers.IO){
            var list = apiService.getSimpleVideoByChannelId(channelId,page,pagesize)
            list
        }
    }
    suspend fun getSimpleType():ResponseData<List<VideoTypeEntity>>{
        return withContext(Dispatchers.IO){
          var list = apiService.getSimpleType()
            list
        }
    }
}