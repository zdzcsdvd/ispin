package com.bw.ispin.example.mymvi

import com.google.android.material.tabs.TabLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.bw.ispin.R
import com.bw.ispin.databinding.Fragment1Binding
import com.bw.ispin.databinding.FragmentFangyBinding
import com.bw.ispin.example.mymvi.adapter.FragAdapter
import com.bw.ispin.example.mymvi.adapter.VideAdapter
import com.bw.ispin.example.mymvi.adapter.VideoAdapter
import com.bw.ispin.example.mymvi.entity.VideoData
import com.bw.ispin.example.mymvi.state.VideoUiState
import com.bw.ispin.example.mymvi.viewmodel.VideoIntent
import com.bw.ispin.example.mymvi.viewmodel.VideoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FangyFragment : Fragment() {
    lateinit var binding:FragmentFangyBinding
    lateinit var vm: VideoViewModel
    lateinit var videoAdapter: VideAdapter
    lateinit var list: List<VideoData>
    var mFragAdapter: FragAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFangyBinding.inflate(layoutInflater)
        val tab = binding.tab
        binding.tab.addTab(tab.newTab().setText("电影"))
        binding.tab.addTab(tab.newTab().setText("电视剧"))
        binding.tab.addTab(tab.newTab().setText("纪录片"))
        binding.tab.addTab(tab.newTab().setText("综艺"))
        binding.tab.addTab(tab.newTab().setText("少儿"))
        binding.tab.addTab(tab.newTab().setText("动漫"))

        vm = ViewModelProvider(this).get(VideoViewModel::class.java)

        list = arrayListOf()
        videoAdapter = VideAdapter(requireContext(), list, object : VideAdapter.ClickListener {
            override fun onClick(position: Int, entity: VideoData) {
                ARouter.getInstance().build("/app/Main3Activity").withSerializable("a",entity).navigation()
            }
        })
        binding.r1.adapter = videoAdapter
        binding.r1.layoutManager =StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


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
            vm.channel.send(VideoIntent.getVideos("94349546935", 10, 40))
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