package com.abc.designsample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.abc.designsample.adapter.MyFragmentPagerAdapter;
import com.abc.designsample.fragments.FragmentCard;
import com.abc.designsample.fragments.FragmentOne;
import com.lzm.designsample.R;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements FragmentOne.OnFragmentInteractionListener,
        FragmentCard.OnFragmentInteractionListener {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setTitle("My Title");
//        setTitle("My Title");
//        toolbar.setSubtitle("Sub title");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
//        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab3"));


        viewPager= (ViewPager) findViewById(R.id.viewpager);

        fragmentList=new ArrayList<Fragment>();
        String [] titles = new String[4];

        for(int i=0;i<=3;i++){
            titles[i]="第"+i+"个";
        }
        for(int i=0;i<=1;i++){
            Fragment fragment= FragmentOne.newInstance(String.valueOf(i),"第"+i+"个");
            Fragment fragment2= FragmentCard.newInstance(String.valueOf(i),"Card");
            fragmentList.add(fragment);
            fragmentList.add(fragment2);
        }

//        tabLayout.setTabTextColors(Color.BLACK, Color.WHITE);

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titles));
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                tabLayout.setScrollPosition(position,positionOffset,true);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    public void onFragmentInteraction(String uri) {

        Toast.makeText(getApplicationContext(),uri,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id ==  android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
