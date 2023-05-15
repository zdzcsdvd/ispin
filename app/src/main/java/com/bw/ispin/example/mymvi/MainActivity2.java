package com.bw.ispin.example.mymvi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.bw.ispin.R;

import java.util.Timer;

public class MainActivity2 extends AppCompatActivity {
    private TextView mView;
    private int i = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mView = findViewById(R.id.t1);
        handler.sendEmptyMessageDelayed(0,1000);


    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            i --;
            mView.setText("倒计时"+i+"S");
            if (i==0){
                startActivity(new Intent(getApplicationContext(),SlashActivity.class));
            }else {
                handler.sendEmptyMessageDelayed(0,1000);
            }
        }
    };

}