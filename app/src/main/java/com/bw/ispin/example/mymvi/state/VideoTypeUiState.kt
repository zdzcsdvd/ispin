package com.bw.ispin.example.mymvi.state

import com.bw.ispin.example.mymvi.entity.VideoData
import com.example.repofitutils.entity.VideoTypeEntity

open class VideoTypeUiState {
    data class Success(var list:List<VideoTypeEntity>?):VideoTypeUiState()
    data class Error(var ex:Throwable?):VideoTypeUiState()
    data class Fail(var msg:String?):VideoTypeUiState()
    object  Loading:VideoTypeUiState()
}