package com.abc.designsample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/11/10.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    String[] titles;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }


    @Override
    public CharSequence getPageTitle(int position) {


        return titles[position];
    }

//    boolean canScrollVertically(int position, int direction) {
//        return getItem(position).canScrollVertically(direction);
//    }
}
