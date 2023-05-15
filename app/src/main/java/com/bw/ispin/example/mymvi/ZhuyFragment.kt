package com.bw.ispin.example.mymvi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.bw.ispin.databinding.FragmentZhuyBinding
import com.bw.ispin.example.mymvi.adapter.FragAdapter
import com.bw.ispin.example.mymvi.adapter.VideoAdapter
import com.bw.ispin.example.mymvi.state.VideoTypeUiState
import com.bw.ispin.example.mymvi.state.VideoUiState
import com.bw.ispin.example.mymvi.viewmodel.VideoIntent
import com.bw.ispin.example.mymvi.viewmodel.VideoViewModel
import com.example.repofitutils.entity.VideoTypeEntity
import kotlinx.coroutines.launch
import java.util.ArrayList

class ZhuyFragment : Fragment() {

    lateinit var vm: VideoViewModel
    lateinit var videoAdapter: VideoAdapter
    lateinit var list:List<VideoTypeEntity>
    var bind: FragmentZhuyBinding?=null
    val binding get() = bind!!
    val mList1: MutableList<String> = ArrayList()
    val list1: MutableList<Fragment> = ArrayList()
    val mList: MutableList<String> = ArrayList()
    var mFragAdapter: FragAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentZhuyBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        vm=ViewModelProvider(this).get(VideoViewModel::class.java)
        mList1.add("彩虹葡头糖")
        mList1.add("长大之后我就成为了你")
        mList1.add("明星代言将何去何从")

        mList.add("游戏")
        mList.add("影视")
        mList.add("音乐")
        mList.add("VLOQ")
        mList.add("农人")
        mList.add("美食")
        mList.add("搞笑")
        mList.add("宠物")
        list1.add(Fragment1())
        list1.add(Fragment2())
        list1.add(Fragment3())
        list1.add(Fragment4())
        list1.add(Fragment5())


        mFragAdapter = FragAdapter(childFragmentManager,list1,mList)
        bind!!.viewpage.setAdapter(mFragAdapter)
        bind!!.tab.setupWithViewPager(bind!!.viewpage)
        bind!!.e1.setDatas(mList1)

        return binding.root
    }

}