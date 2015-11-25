package com.abc.designsample;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzm.designsample.R;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Main3Activity extends AppCompatActivity {

    private Button btn1,btn2,btnSweet;
    private TextView tvCount;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initView();
        initTimer();
        timer.cancel();
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //tvCount.setText("还剩下16秒");
                //timer.start();
                Intent intent=new Intent(Main3Activity.this, ScrollableActivity.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog();
            }
        });

        btnSweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(Main3Activity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // reuse previous dialog instance
                                sDialog.setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
            }
        });

    }

    private void initTimer(){
        timer=new CountDownTimer(16000L,1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("OK","onTick>>>>"+millisUntilFinished);
                double i=millisUntilFinished/1000.00;
                Log.e("OK","除以1000>>>"+i);
                int second= (int) Math.round(i);

                tvCount.setText("还剩下" + (second-1)+"秒");
            }

            @Override
            public void onFinish() {
                tvCount.setText("Bingo~");
            }
        };
    }



    private void initView() {
        btn1 = (Button) findViewById(R.id.button_1);
        btn2 = (Button) findViewById(R.id.button_2);
        btnSweet = (Button) findViewById(R.id.button_sweet);
        tvCount = (TextView) findViewById(R.id.textView_count);
    }

    private void initDialog() {
        final MaterialDialog dialog = new MaterialDialog(Main3Activity.this);
        dialog.isTitleShow(false)//
                .content("Are you sure to exit app?")//
                .contentTextSize(18)
                .btnText("Exit", "Cancel")//
                .showAnim(new BounceTopEnter())//
                .dismissAnim(new SlideBottomExit())//
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                btn1.setText("Exit");
                dialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                btn1.setText("Cancel");
                dialog.dismiss();
            }
        });

    }


}
