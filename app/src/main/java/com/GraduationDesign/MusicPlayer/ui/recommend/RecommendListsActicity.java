package com.GraduationDesign.MusicPlayer.ui.recommend;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.RxBus;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.data.model.PlayList;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.event.PlaySongEvent;
import com.GraduationDesign.MusicPlayer.ui.base.BaseActivity;
import com.GraduationDesign.MusicPlayer.ui.playlist.AddToPlayListDialogFragment;
import com.GraduationDesign.MusicPlayer.ui.recommend.Adapter.RecommendListAdapter;
import com.GraduationDesign.MusicPlayer.utils.WyRecommendUtil;

public class RecommendListsActicity extends BaseActivity {
    private String ListId,Key,ListName;
    WyRecommendListBean songList;
    RecyclerView songlist;
    RecommendListAdapter recommendListAdapter;
    LinearLayout emptyNotice;
    ProgressBar recommendLoading;
    Toolbar toolbar;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Intent intent = getIntent();
        ListId = intent.getStringExtra("ListId");
        Key = intent.getStringExtra("Key");
        ListName = intent.getStringExtra("ListName");
        setContentView(R.layout.activity_recommend_lists_acticity);
        emptyNotice = findViewById(R.id.ll_empty_notify);
        songlist = findViewById(R.id.recommend_list_detail);
        recommendLoading = findViewById(R.id.recommend_loading);
        toolbar = findViewById(R.id.recommend_toolbar);
        supportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(ListName);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(ListName);
        }
        songlist.setLayoutManager(new LinearLayoutManager(this));

        emptyNotice.setVisibility(View.INVISIBLE);
        recommendLoading.setVisibility(View.VISIBLE);
        songlist.setVisibility(View.INVISIBLE);
        initData();


    }

    public void initData(){
        CommonApi.getWyRecommendById(ListId,Key, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                songList = (WyRecommendListBean) o;
                if(songList.getCode().equals("OK")){
                    if(songList.getBody()!=null)
                        mhandler.sendMessage(mhandler.obtainMessage(1));
                    else {
                        mhandler.sendMessage(mhandler.obtainMessage(2));
                    }
                }
                else mhandler.sendMessage(mhandler.obtainMessage(2));

            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void initAdapter(){
        initAinm();

        recommendListAdapter = new RecommendListAdapter(this, songList.getBody(), new RecommendItemLitener() {
            @Override
            public void OnClickItem(final Song song) {

                Log.e("MusicUrl A",song.getPath());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String path = WyRecommendUtil.getRedirectUrl(song.getPath());
//                        song.setPath(path);
                        Log.e("MusicUrl B",song.getPath());
                        RxBus.getInstance().post(new PlaySongEvent(song));
                    }
                }).start();

            }
        });
        recommendListAdapter.setActionCallback(new RecommendListAdapter.ActionCallback() {
            @Override
            public void onAction(View actionView, Song song) {
                final Song tmpsong = song;
                PopupMenu actionMenu = new PopupMenu(mContext, actionView, Gravity.END | Gravity.BOTTOM);
                actionMenu.inflate(R.menu.music_action);
                actionMenu.getMenu().findItem(R.id.menu_item_delete).setVisible(false);
                actionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_item_add_to_play_list:
                                new AddToPlayListDialogFragment()
                                        .setCallback(new AddToPlayListDialogFragment.Callback() {
                                            @Override
                                            public void onPlayListSelected(PlayList playList) {
//                                                mPresenter.addSongToPlayList(song, playList);
                                            }
                                        })
                                        .show(getSupportFragmentManager().beginTransaction(), "AddToPlayList");
                                break;
                            case R.id.menu_item_delete:
                                break;
                        }
                        return true;
                    }
                });
                actionMenu.show();
            }
        });
        songlist.setAdapter(recommendListAdapter);
        mhandler.sendMessage(mhandler.obtainMessage(3));
    }

    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    initAdapter();
                    break;
                case 2:
                    songlist.setVisibility(View.INVISIBLE);
                    recommendLoading.setVisibility(View.GONE);
                    emptyNotice.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    songlist.setVisibility(View.VISIBLE);
                    recommendLoading.setVisibility(View.GONE);
                    emptyNotice.setVisibility(View.INVISIBLE);
                    break;
            }
            return false;
        }
    });
    private void initAinm() {
        //通过加载XML动画设置文件来创建一个Animation对象；
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.recycleview_item_left);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(0.2f);
        //为ListView设置LayoutAnimationController属性；
        songlist.setLayoutAnimation(lac);
    }

}
