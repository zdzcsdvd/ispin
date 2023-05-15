package com.bw.ispin.example.mymvi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private List<String> mList_title;
    public FragAdapter(@NonNull FragmentManager fm, List<Fragment> list, List<String> list_title) {
        super(fm);
        this.mList = list;
        this.mList_title = list_title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mList_title.get(position);
    }
}
