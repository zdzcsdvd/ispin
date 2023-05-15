package com.bw.ispin.example.mymvi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.ispin.R
import com.bw.ispin.databinding.Fragment1Binding
import com.bw.ispin.databinding.Fragment2Binding
import com.bw.ispin.example.mymvi.adapter.FragAdapter
import com.bw.ispin.example.mymvi.adapter.VideoAdapter
import com.bw.ispin.example.mymvi.entity.VideoData
import com.bw.ispin.example.mymvi.state.VideoUiState
import com.bw.ispin.example.mymvi.viewmodel.VideoIntent
import com.bw.ispin.example.mymvi.viewmodel.VideoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Fragment2 : Fragment() {
    lateinit var binding: Fragment2Binding
    lateinit var vm: VideoViewModel
    lateinit var videoAdapter: VideoAdapter
    lateinit var list: List<VideoData>
    var mFragAdapter: FragAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment2Binding.inflate(layoutInflater)
        vm = ViewModelProvider(this).get(VideoViewModel::class.java)

        list = arrayListOf()
        videoAdapter = VideoAdapter(requireContext(), list, object : VideoAdapter.ClickListener {
            override fun onClick(position: Int, entity: VideoData) {
                ARouter.getInstance().build("/app/Main3Activity").withSerializable("a",entity).navigation()
            }
        })
        binding.tr.adapter = videoAdapter
        binding.tr.layoutManager = LinearLayoutManager(context)


        lifecycleScope.launch {
            //接收到响应状态
            vm.uiState.collect { uiState ->
                when (uiState) {
                    is VideoUiState.Loading -> {
                        Log.i("===", "加载中……")
                    }
                    is VideoUiState.Success -> {
                        //更新UI
                        updateUI(uiState)
                    }

                }
            }
        }

        lifecycleScope.launch {
            //发送请求列表意图
            vm.channel.send(VideoIntent.getVideos("94349546935", 10, 200))
        }
        return binding.root
    }
    private fun updateUI(uiState: VideoUiState.Success) {
        if(uiState.list==null){
            return
        }
        videoAdapter.data=uiState.list!!
        videoAdapter.notifyDataSetChanged()
    }
}