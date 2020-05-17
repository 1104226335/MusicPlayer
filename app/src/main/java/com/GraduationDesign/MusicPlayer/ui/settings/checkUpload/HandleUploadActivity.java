package com.GraduationDesign.MusicPlayer.ui.settings.checkUpload;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.RxBus;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyCommentBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyMusicBean;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.event.PlaySongEvent;
import com.GraduationDesign.MusicPlayer.ui.base.BaseActivity;
import com.GraduationDesign.MusicPlayer.ui.settings.checkcomment.CheckCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandleUploadActivity extends BaseActivity implements HandleUploadAdapter.OnAction {
    @BindView(R.id.check_list_detail)
    RecyclerView musicCheck;
    @BindView(R.id.toolbar_music_check)
    Toolbar toolbar;

    HandleUploadAdapter handleUploadAdapter;
    private List<MyMusicBean.ResultBean> resultBean = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_upload);
        ButterKnife.bind(this);
        supportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("审核音乐");
        }
        musicCheck.setLayoutManager(new LinearLayoutManager(this));
        handleUploadAdapter = new HandleUploadAdapter(this,resultBean);
        handleUploadAdapter.setAction(this);
        musicCheck.setAdapter(handleUploadAdapter);
        initData();
    }
    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    handleUploadAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    handleUploadAdapter.notifyItemRemoved(msg.arg1);
                    handleUploadAdapter.notifyItemRangeChanged(msg.arg1,resultBean.size()-msg.arg1);
            }
            return false;
        }
    });
    public void initData(){
        CommonApi.checkMyUpload("check", 0, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.e("MusicPlayer",o.toString());
                MyMusicBean myMusicBean = (MyMusicBean)o;
                resultBean.clear();
                resultBean.addAll(myMusicBean.getResult());
                mhandler.sendMessage(mhandler.obtainMessage(1));
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void display(Song songs) {
        RxBus.getInstance().post(new PlaySongEvent(songs));
    }

    @Override
    public void onAction(View v,final int musicId, final int position) {
        PopupMenu actionMenu = new PopupMenu(this, v, Gravity.END | Gravity.BOTTOM);
        actionMenu.inflate(R.menu.check_action);
        actionMenu.getMenu().findItem(R.id.menu_item_feedback).setVisible(false);
        actionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_pass:
                        commentOp("pass",musicId,position);
                        break;
                    case R.id.menu_item_failed_pass:
                        commentOp("delete",musicId,position);
                        break;
                }
                return true;
            }
        });
        actionMenu.show();
    }
    public void commentOp(String type,int musicId, final int positon){
        CommonApi.checkMyUpload(type, musicId, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.e("MusicCheck","返回成功");
                MyMusicBean myMusicBean = (MyMusicBean)o;
                if(myMusicBean.getError()==0){
                    TextHelper.showText("操作成功");
                    Log.e("MusicCheck","操作成功");
                }
                resultBean.remove(positon);
                Message message = mhandler.obtainMessage(2);
                message.arg1 = positon;
                mhandler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
