package com.bw.ispin.example.mymvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.bw.ispin.databinding.ActivityMainBinding
import com.bw.ispin.example.mymvi.entity.VideoData
import com.bw.ispin.example.mymvi.adapter.VideoAdapter
import com.bw.ispin.example.mymvi.state.VideoUiState
import com.bw.ispin.example.mymvi.viewmodel.VideoIntent
import com.bw.ispin.example.mymvi.viewmodel.VideoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var vm:VideoViewModel
    lateinit var videoAdapter: VideoAdapter
    lateinit var list:List<VideoData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm=ViewModelProvider(this).get(VideoViewModel::class.java)

        list= arrayListOf()
        videoAdapter= VideoAdapter(this,list,object :VideoAdapter.ClickListener{
            override fun onClick(position: Int, entity: VideoData) {
                ToastUtils.showLong(entity.title)
                ARouter.getInstance().build("/video/MainActivity").navigation()
            }
        })
        binding.tv.adapter=videoAdapter
        binding.tv.layoutManager=LinearLayoutManager(this)


        lifecycleScope.launch {
            //接收到响应状态
            vm.uiState.collect { uiState->
                when(uiState){
                    is VideoUiState.Loading->{
                        Log.i("===","加载中……")
                    }
                    is VideoUiState.Success->{
                        //更新UI
                        updateUI(uiState)
                    }

                }
            }
        }

        lifecycleScope.launch {
            //发送请求列表意图
            vm.channel.send(VideoIntent.getVideos("94349546935",1,10))
        }
    }

    private fun updateUI(uiState: VideoUiState.Success) {
        if(uiState.list==null){
            return
        }
        videoAdapter.data=uiState.list!!
        videoAdapter.notifyDataSetChanged()
    }
}