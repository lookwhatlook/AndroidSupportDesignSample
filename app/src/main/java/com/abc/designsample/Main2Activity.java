package com.abc.designsample;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.abc.designsample.adapter.MyFragmentPagerAdapter;
import com.abc.designsample.fragments.FragmentCard;
import com.abc.designsample.fragments.FragmentOne;
import com.lzm.designsample.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import java.util.ArrayList;

public class Main2Activity extends BaseActivity implements FragmentOne.OnFragmentInteractionListener,
        FragmentCard.OnFragmentInteractionListener {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;
    private AccountHeader headerResult;

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }
//
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);

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

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.mipmap.admin_page_default_head)
                .withSavedInstance(savedInstanceState)
                .build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withName("Home");

        SecondaryDrawerItem item2= (SecondaryDrawerItem) new SecondaryDrawerItem().withIcon(R.drawable.ic_launcher).withName("EMAIL");;
        new DrawerBuilder()
                .withActivity(this)
//                .withRootView(R.id.drawer_layout)
//                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withFullscreen(true)
//                .withTranslucentStatusBar(true)
                .addDrawerItems(item1, item2)
//                .withShowDrawerOnFirstLaunch(true)
//                .withHeaderPadding(true)
//                .withSystemUIHidden(false)
                .build();


//        viewPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onFragmentInteraction(String uri) {

        Toast.makeText(getApplicationContext(),uri,Toast.LENGTH_SHORT).show();
    }


}
