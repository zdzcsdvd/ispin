package com.bw.ispin.example.mymvi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.ispin.R;
import com.bw.ispin.example.mymvi.entity.Record;
import com.bw.ispin.example.mymvi.entity.VideoData;
import com.google.android.material.tabs.TabLayout;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;

@Route(path = "/app/Main3Activity")
public class MainActivity3 extends AppCompatActivity {
    private ArrayList<Fragment> mList = new ArrayList<>();
    private ArrayList<String> mList1 = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer mView;
    @Autowired
    VideoData a;
    StandardGSYVideoPlayer mStandardGSYVideoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ARouter.getInstance().inject(this);
        mStandardGSYVideoPlayer = findViewById(R.id.video);
        mStandardGSYVideoPlayer.setUp(a.getVideopath().toString(),true,"");
        mStandardGSYVideoPlayer.startPlayLogic();
        mStandardGSYVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mTabLayout = findViewById(R.id.ss);




    }

}