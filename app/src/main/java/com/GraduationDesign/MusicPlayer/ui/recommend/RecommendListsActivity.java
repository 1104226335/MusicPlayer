package com.GraduationDesign.MusicPlayer.ui.recommend;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.RxBus;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyRecommendListBean;
import com.GraduationDesign.MusicPlayer.data.model.PlayList;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.data.source.AppRepository;
import com.GraduationDesign.MusicPlayer.event.PlayListNowEvent;
import com.GraduationDesign.MusicPlayer.event.PlayListUpdatedEvent;
import com.GraduationDesign.MusicPlayer.ui.base.BaseActivity;
import com.GraduationDesign.MusicPlayer.ui.playlist.AddToPlayListDialogFragment;
import com.GraduationDesign.MusicPlayer.ui.recommend.Adapter.RecommendListAdapter;
import com.GraduationDesign.MusicPlayer.utils.BlurUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.security.MessageDigest;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class RecommendListsActivity extends BaseActivity {
    private String ListId,Key,ListName,ListPic;
    WyRecommendListBean songList;
    RecyclerView songlist;
    RecommendListAdapter recommendListAdapter;
    LinearLayout emptyNotice;
    ProgressBar recommendLoading;
    Toolbar toolbar;
    ImageView listPic,clearPic;
    Context mContext;

    private CompositeSubscription mSubscriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Intent intent = getIntent();
        ListId = intent.getStringExtra("ListId");
        Key = intent.getStringExtra("Key");
        ListName = intent.getStringExtra("ListName");
        ListPic = intent.getStringExtra("ListPic");
        setContentView(R.layout.activity_recommend_lists_acticity);
        emptyNotice = findViewById(R.id.ll_empty_notify);
        songlist = findViewById(R.id.recommend_list_detail);
        recommendLoading = findViewById(R.id.recommend_loading);
        toolbar = findViewById(R.id.recommend_toolbar);
        listPic = findViewById(R.id.iv_recommend_list_pic);
        clearPic = findViewById(R.id.iv_recommend_pic);
        supportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(ListName);
            getSupportActionBar().setSubtitle("推荐");
        }
        songlist.setLayoutManager(new LinearLayoutManager(this));

        setListPicture();
        emptyNotice.setVisibility(View.INVISIBLE);
        recommendLoading.setVisibility(View.VISIBLE);
        songlist.setVisibility(View.INVISIBLE);

        initData();
        mSubscriptions = new CompositeSubscription();
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
            public void OnClickItem(final PlayList songs, final int position) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RxBus.getInstance().post(new PlayListNowEvent(songs,position));
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
                actionMenu.getMenu().findItem(R.id.menu_item_update).setVisible(false);
                actionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_item_add_to_play_list:
                                new AddToPlayListDialogFragment()
                                        .setCallback(new AddToPlayListDialogFragment.Callback() {
                                            @Override
                                            public void onPlayListSelected(PlayList playList) {
                                                addSongToPlayList(tmpsong, playList);
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
    public void setListPicture(){
        Glide.with(this)
                .load(ListPic)
                .into(clearPic);
        Glide.with(this)
                .load(ListPic)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.bro1)
                        .error(R.mipmap.bro1)
                        .transform(new BitmapTransformation() {
                            @Override
                            protected Bitmap transform(@NonNull BitmapPool pool,
                                                       @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                                // 对得到的 Bitmap 进行虚化处理
                                return BlurUtil.blurBitmap(getApplicationContext(),
                                        toTransform, 5, 8);
                            }

                            @Override
                            public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

                            }
                        }))
                .into(listPic);
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
    public void addSongToPlayList(Song song, PlayList playList) {
        if (playList.isFavorite()) {
            song.setFavorite(true);
        }
        playList.addSong(song, 0);
        Subscription insertSubscription = AppRepository.getInstance().insert(song)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Song>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Song song) {

                    }
                });
        mSubscriptions.add(insertSubscription);
        Subscription subscription = AppRepository.getInstance().update(playList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PlayList>() {
                    @Override
                    public void onStart() {
                        showProgress();
                    }

                    @Override
                    public void onCompleted(){
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgress();
                        handleError(e);
                    }

                    @Override
                    public void onNext(PlayList playList) {
                        RxBus.getInstance().post(new PlayListUpdatedEvent(playList));
                    }
                });
        mSubscriptions.add(subscription);
    }
    public void showProgress() {
        recommendLoading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        recommendLoading.setVisibility(View.GONE);
    }
    public void handleError(Throwable error) {
        TextHelper.showLongText(error.getMessage());
    }
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }
}
