package com.GraduationDesign.MusicPlayer.ui.recommend;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;

public class RecommendListsActicity extends AppCompatActivity {
    private String ListId;
    WyRecommendListBean songList;
    RecyclerView songlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ListId = intent.getStringExtra("ListId");
        setContentView(R.layout.activity_recommend_lists_acticity);
        songlist = findViewById(R.id.recommend_list_detail);
        songlist.setLayoutManager(new LinearLayoutManager(this));




    }

    public void initData(){
        CommonApi.getWyRecommendById(ListId, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                songList = (WyRecommendListBean) o;

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:

                    break;
            }
            return false;
        }
    });
}
