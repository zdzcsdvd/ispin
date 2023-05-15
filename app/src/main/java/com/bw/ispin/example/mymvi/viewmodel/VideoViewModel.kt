package com.bw.ispin.example.mymvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bw.ispin.example.mymvi.repository.VideoRepo
import com.bw.ispin.example.mymvi.state.VideoTypeUiState
import com.bw.ispin.example.mymvi.state.VideoUiState
import com.example.repofitutils.entity.VideoTypeEntity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

sealed class VideoIntent{
    data class getVideos(val channelId:String,val page:Int,val pagesize:Int):VideoIntent()
    data class getTypeVideo(val channelId:String) :VideoIntent()
    object getDetail:VideoIntent()
}

class VideoViewModel:ViewModel() {
    var videoRepo=VideoRepo()
    //获取一个channel
    val channel= Channel<VideoIntent>(Channel.UNLIMITED)
    private val _uiState=MutableStateFlow<VideoUiState>(VideoUiState.Loading)
    private val _uiTypeState=MutableStateFlow<VideoTypeUiState>(VideoTypeUiState.Loading)
    val uiState: StateFlow<VideoUiState>
        get() = _uiState
    val uiTypeState:StateFlow<VideoTypeUiState>
        get() = _uiTypeState
    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect {
                when(it){
                    is VideoIntent.getVideos -> getVideos(it.channelId,it.page,it.pagesize)
                }

            }
        }
    }

    private fun getVideos(channelId:String,page:Int,pagesize:Int) {
        viewModelScope.launch {
            val data = videoRepo.getSimpleVideoByChannelId(channelId, page, pagesize)
            if(data.code==0){
                _uiState.value=VideoUiState.Success(data?.data)
            }else{
                _uiState.value=VideoUiState.Fail(data.msg)
            }
        }
    }

    init {
        handlerTypeIntent()
    }
    private fun handlerTypeIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect {
                when(it){
                    is VideoIntent.getTypeVideo -> getVideType()
                }

            }
        }
    }

    private fun getVideType() {
        viewModelScope.launch {
            val data = videoRepo.getSimpleType()
            if(data.code==0){
                _uiTypeState.value=VideoTypeUiState.Success(data?.data)
            }else{
                _uiTypeState.value=VideoTypeUiState.Fail(data.msg)
            }
        }
    }
}