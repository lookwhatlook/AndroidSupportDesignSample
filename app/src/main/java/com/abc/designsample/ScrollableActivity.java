package com.abc.designsample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.lzm.designsample.R;
import com.abc.designsample.adapter.MyFragmentPagerAdapter;
import com.abc.designsample.common.BannerItem;
import com.abc.designsample.common.DataProvider;
import com.abc.designsample.fragments.FragmentCard;
import com.abc.designsample.fragments.FragmentOne;
import com.abc.widget.SimpleImageBanner;

import java.util.ArrayList;

import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

public class ScrollableActivity extends BaseActivity implements FragmentOne.OnFragmentInteractionListener,
        FragmentCard.OnFragmentInteractionListener {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;
    private ScrollableLayout mScrollableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        mScrollableLayout = (ScrollableLayout) findViewById(R.id.scrollable_layout);
        mScrollableLayout.setDraggableView(tabLayout);

        fragmentList = new ArrayList<Fragment>();
        String[] titles = new String[7];

        for (int i = 0; i <= 6; i++) {
            titles[i] = "第" + i + "个";
        }
        for (int i = 0; i <= 6; i++) {
//            Fragment fragment = FragmentOne.newInstance(String.valueOf(i), "第" + i + "个");
            Fragment fragment2 = FragmentCard.newInstance(String.valueOf(i), "Card");
//            fragmentList.add(fragment);
            fragmentList.add(fragment2);
        }
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


//        mScrollableLayout.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
//            @Override
//            public boolean canScrollVertically(int direction) {
//
////                return adapter.canScrollVertically(viewPager.getCurrentItem(), direction);
//            }
//        });

        final View view= findViewById(R.id.sib_indicator_right_with_text);
        mScrollableLayout.setOnScrollChangedListener(new OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int y, int oldY, int maxY) {

                float tabsTransitionY = y < maxY ? 0 : y - maxY;


                view.setTranslationY(tabsTransitionY);
            }
        });
        sib_indicator_right_with_text();

    }

    private void sib_indicator_right_with_text() {
        SimpleImageBanner sib = (SimpleImageBanner) findViewById(R.id.sib_indicator_right_with_text);
        sib.setSource(DataProvider.getList()).startScroll();

        final ArrayList<BannerItem> list=DataProvider.getList();
        sib.setOnItemClickL(new SimpleImageBanner.OnItemClickL() {
            @Override
            public void onItemClick(int position) {
                String msg=list.get(position).link;
                Toast.makeText(ScrollableActivity.this, "position--->" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onFragmentInteraction(String uri) {

    }
}
