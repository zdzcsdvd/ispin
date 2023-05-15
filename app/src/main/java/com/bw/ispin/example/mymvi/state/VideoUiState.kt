package com.bw.ispin.example.mymvi.state

import com.bw.ispin.example.mymvi.entity.VideoData

sealed class VideoUiState {
    data class Success(var list:List<VideoData>?):VideoUiState()
    data class Error(var ex:Throwable?):VideoUiState()
    data class Fail(var msg:String?):VideoUiState()
    object  Loading:VideoUiState()

}