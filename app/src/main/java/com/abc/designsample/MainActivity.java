package com.abc.designsample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.lzm.designsample.R;
import com.abc.designsample.adapter.RecyclerAdapter;
import com.abc.designsample.common.DividerItemDecoration;
//import com.nispok.snackbar.Snackbar;
//import com.nispok.snackbar.SnackbarManager;
//import com.nispok.snackbar.listeners.ActionClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextInputLayout textinput;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private long exitTime;
    private FloatingActionButton fab;
    com.github.clans.fab.FloatingActionButton fab12;
    com.github.clans.fab.FloatingActionButton fab22;
    com.github.clans.fab.FloatingActionButton fab32;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initInputLayout();
        initDrawLayout();

//        actionBar.setHomeAsUpIndicator(R.mipmap.user_center_icon_logout_pressed);
//        toolbar.setNavigationIcon(R.mipmap.user_center_icon_logout_normal);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showSnackBar(v);
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showSnackBar(view);
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("林晓婧");
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);//设置收缩后Toolbar上字体的颜色

        recyclerView = (RecyclerView) findViewById(R.id.rvToDoList);
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));

        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i <= 20; i++) {
            list.add("这个是00" + i);
        }

        RecyclerAdapter mAdapter = new RecyclerAdapter(list, this);
        // 为mRecyclerView设置适配器
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                MainActivity.this, DividerItemDecoration.VERTICAL_LIST));
//        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        initFloatMenu();
    }

    private void initFloatMenu(){
        final FloatingActionMenu menu2 = (FloatingActionMenu) findViewById(R.id.menu2);
//        menu2.hideMenuButton(false);
        menu2.setClosedOnTouchOutside(true);
        menu2.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                String text = "";
                if (opened) {
                    text = "Menu opened";
                } else {
                    text = "Menu closed";
                }
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });



        fab12 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab12);
        fab22 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab22);
        fab32 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab32);

        fab12.setOnClickListener(clickListener);
        fab22.setOnClickListener(clickListener);
        fab32.setOnClickListener(clickListener);


    }

    private void initDrawLayout() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
                mDrawerLayout,
                R.string.open_,
                R.string.close_);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.item_one:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.item_two:
                        break;
                    case R.id.item_three:
                        break;
                }

                return false;
            }
        });
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = "";

            switch (v.getId()) {

                case R.id.fab12:
                    text = fab12.getLabelText();
                    break;
                case R.id.fab22:
                    text = fab22.getLabelText();
                    break;
                case R.id.fab32:
                    text = fab32.getLabelText();
                    break;
            }

            Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
        }
    };

    private void initInputLayout() {
        textinput = (TextInputLayout) findViewById(R.id.til_pwd);

        EditText editText = textinput.getEditText();
//        editText.setHint("用户名");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 4) {
                    textinput.setErrorEnabled(true);
                    textinput.setError("Password error");
//                    Log.e("OK", "大于444444");
                } else {
                    textinput.setError("");
//                    textinput.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (s.length() > 4) {
//                    textinput.setErrorEnabled(true);
//                    textinput.setError("Password error");
//
//                } else {
//                    textinput.setError("");
////                    textinput.setErrorEnabled(false);
//                }
            }
        });


        textinput.setHint("请输入用户名");
//        textinput.setError("密码输入错啦！");
//        textinput.setErrorEnabled(true);//当设置成false的时候 错误信息不显示 反之显示
    }

    private void showSnackBar(View view) {

        Snackbar.make(view, "是否退出程序？", Snackbar.LENGTH_SHORT)
                .setAction("确定", new ExitActitity()).show();

//下面是使用第三方snackbar的用法
//        SnackbarManager.show(Snackbar.with(MainActivity.this).text("再按一次退出程序")
//
//                .actionLabel("确定").actionColor(Color.RED).actionListener(new ActionClickListener() {
//                    @Override
//                    public void onActionClicked(Snackbar snackbar) {
//                        onBackPressed();
//                    }
//                }).dismissAnimation(true).duration(Snackbar.SnackbarDuration.LENGTH_SHORT));

    }

    private class ExitActitity implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            MainActivity.this.finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
               // showSnackBar(null);
                exitTime = System.currentTimeMillis();

                Snackbar.make( fab, "再按一次退出程序", Snackbar.LENGTH_SHORT)
                        .setAction("确定", new ExitActitity()).show();

            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
