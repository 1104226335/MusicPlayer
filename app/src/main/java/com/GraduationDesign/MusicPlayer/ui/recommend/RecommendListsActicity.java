package com.GraduationDesign.MusicPlayer.ui.recommend;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.RxBus;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.event.PlaySongEvent;
import com.GraduationDesign.MusicPlayer.utils.WyRecommendUtil;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import okhttp3.internal.http.StatusLine;

public class RecommendListsActicity extends AppCompatActivity {
    private String ListId,Key;
    WyRecommendListBean songList;
    RecyclerView songlist;
    RecommendListAdapter recommendListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        ListId = intent.getStringExtra("ListId");
        Key = intent.getStringExtra("Key");
        setContentView(R.layout.activity_recommend_lists_acticity);
        songlist = findViewById(R.id.recommend_list_detail);
        songlist.setLayoutManager(new LinearLayoutManager(this));

        initData();


    }

    public void initData(){
        CommonApi.getWyRecommendById(ListId,Key, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                songList = (WyRecommendListBean) o;
                mhandler.sendMessage(mhandler.obtainMessage(1));
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void initAdapter(){
        recommendListAdapter = new RecommendListAdapter(this, songList.getBody(), new RecommendItemLitener() {
            @Override
            public void OnClickItem(final Song song) {

                Log.e("MusicUrl A",song.getPath());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String path = WyRecommendUtil.getRedirectUrl(song.getPath());
                        song.setPath(path);
                        Log.e("MusicUrl B",song.getPath());
                        RxBus.getInstance().post(new PlaySongEvent(song));
                    }
                }).start();

            }
        });
        songlist.setAdapter(recommendListAdapter);
    }

    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    initAdapter();
                    break;
            }
            return false;
        }
    });


}
