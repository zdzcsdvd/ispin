package com.bw.ispin.example.mymvi.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class FragmentAdapter(fm: FragmentManager, behavior: Int, list: List<Fragment>) :
    FragmentPagerAdapter(fm, behavior) {
    private var mList: List<Fragment> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

    init {
        mList = list
    }
}