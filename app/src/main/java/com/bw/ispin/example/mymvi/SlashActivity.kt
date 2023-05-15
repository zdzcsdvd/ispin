package com.bw.ispin.example.mymvi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bw.ispin.R
import com.bw.ispin.databinding.ActivitySlashBinding
import com.bw.ispin.example.mymvi.adapter.FragmentAdapter
import com.bw.ispin.example.mymvi.viewmodel.VideoViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationBarView
import java.util.*
import kotlin.collections.ArrayList

class SlashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySlashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slash)
        binding = ActivitySlashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(ZhuyFragment())
        fragments.add(FangyFragment())
        fragments.add(ShPFragment())
        fragments.add(ZhiBoFragment())
        fragments.add(WodeFragment())
        val fragmentAdapter = FragmentAdapter(supportFragmentManager, R.id.viewpage, fragments)
        val viewPage: ViewPager = binding.viewpage
        viewPage.setAdapter(fragmentAdapter)
        val bottomNavigationView: BottomNavigationView = binding.botton
        bottomNavigationView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        viewPage.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        bottomNavigationView.selectedItemId = R.id.c1
                    }
                    1->{
                        bottomNavigationView.selectedItemId = R.id.c2
                    }
                    2->{
                        bottomNavigationView.selectedItemId = R.id.c3
                    }
                    3->{
                        bottomNavigationView.selectedItemId = R.id.c4
                    }
                    4->{
                        bottomNavigationView.selectedItemId = R.id.c5
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


        bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            when (it.itemId) {
                R.id.c1 -> viewPage.setCurrentItem(0)
                R.id.c2 -> viewPage.setCurrentItem(1)
                R.id.c3 -> viewPage.setCurrentItem(2)
                R.id.c4 -> viewPage.setCurrentItem(3)
                R.id.c5 -> viewPage.setCurrentItem(4)
        }
            true
        })

    }
}