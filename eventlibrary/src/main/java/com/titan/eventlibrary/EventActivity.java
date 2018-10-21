package com.titan.eventlibrary;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private String lon,lat,address;

    private TextView lonView,latView,addrView,cripeView,remarkView,phoneView,audioView;
    private TextView audioValue,localView,onlineView;
    private RecyclerView phoneRecyc;
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        lon = getIntent().getStringExtra("lon");
        lat = getIntent().getStringExtra("lat");
        address = getIntent().getStringExtra("address");

        initView();

    }


    private void initView(){
        ImageView close = findViewById(R.id.back_close);
        close.setOnClickListener(this);

        lonView = findViewById(R.id.upinfo_lon);
        lonView.setText(lon);

        latView = findViewById(R.id.upinfo_lat);
        latView.setText(lat);

        addrView = findViewById(R.id.upinfo_addr);
        addrView.setText(address);

        cripeView = findViewById(R.id.upinfo_discripe);
        remarkView = findViewById(R.id.upinfo_remark);
        phoneView = findViewById(R.id.upinfo_phone);

        phoneRecyc = findViewById(R.id.recyc_phone);
        audioView = findViewById(R.id.upinfo_audio);
        audioView.setOnClickListener(this);

        audioValue = findViewById(R.id.audio);

        videoView = findViewById(R.id.recycvideo);

        localView = findViewById(R.id.local_sure);
        onlineView = findViewById(R.id.online_sure);
        localView.setOnClickListener(this);
        onlineView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.back_close) {
            EventActivity.this.finish();
        }else if(i == R.id.local_sure){
            localSure();
        }else if(i == R.id.online_sure){
            onlineSure();
        }
    }


    /*本地保存*/
    private void localSure(){


    }

    /*在线上报*/
    private void onlineSure(){

    }



}
