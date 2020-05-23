package com.GraduationDesign.MusicPlayer.ui.settings.checkcomment;

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

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyCommentBean;
import com.GraduationDesign.MusicPlayer.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckCommentActivity extends BaseActivity implements CheckCommentAdapter.OnAction{
    @BindView(R.id.rv_music_comment_check)
    RecyclerView musicComment;
    @BindView(R.id.toolbar_music_comment_check)
    Toolbar toolbar;

    CheckCommentAdapter adapter;
    List<MyCommentBean.ResultBean> checkComments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_comment);
        ButterKnife.bind(this);
        supportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("审核评论");
        }
        musicComment.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CheckCommentAdapter(this,checkComments);
        adapter.setAction(this);
        musicComment.setAdapter(adapter);
        initData();
    }
    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    adapter.notifyItemRemoved(msg.arg1);
                    adapter.notifyItemRangeChanged(msg.arg1,checkComments.size()-msg.arg1);
            }
            return false;
        }
    });
    public void initData(){
        CommonApi.checkMyComment("check","", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                MyCommentBean myCommentBean = (MyCommentBean)o;
                checkComments.clear();
                checkComments.addAll(myCommentBean.getResult());
                mhandler.sendMessage(mhandler.obtainMessage(1));
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void onAction(View v, final int commentId, final int position) {
        PopupMenu actionMenu = new PopupMenu(this, v, Gravity.END | Gravity.BOTTOM);
        actionMenu.inflate(R.menu.check_action);
        actionMenu.getMenu().findItem(R.id.menu_item_feedback).setVisible(false);
        actionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_pass:
                        commentOp("pass",commentId,position);
                        break;
                    case R.id.menu_item_failed_pass:
                        commentOp("delete",commentId,position);
                        break;
                }
                return true;
            }
        });
        actionMenu.show();
    }
    public void commentOp(String type,int commentId, final int positon){
        CommonApi.checkMyComment(type, Integer.toString(commentId), new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.e("MusicCheck","返回成功");
                MyCommentBean myCommentBean = (MyCommentBean)o;
                if(myCommentBean.getError()==0){
                    TextHelper.showText("操作成功");
                    Log.e("MusicCheck","操作成功");
                }
                checkComments.remove(positon);
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
