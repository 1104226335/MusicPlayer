package com.GraduationDesign.MusicPlayer.ui.feedback;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyCommentBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyFeedbackBean;
import com.GraduationDesign.MusicPlayer.ui.feedback.HandleFeedbackAdapter;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HandleFeedbackActivity extends AppCompatActivity implements HandleFeedbackAdapter.OnAction{
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.rv_handle_feedback)
    RecyclerView handle_feedback;


    HandleFeedbackAdapter adapter;
    List<MyFeedbackBean.FResultBean> handlefeedbacks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_feedback);
        toolbar = findViewById(R.id.app_handle_feedback_setting_toolbar);
        setToolbar();


        handle_feedback.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HandleFeedbackAdapter(this,handlefeedbacks);
        adapter.setAction(this);
        handle_feedback.setAdapter(adapter);
        initData();



    }

    //set Toolbar
    public void setToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("处理反馈");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                    adapter.notifyItemRangeChanged(msg.arg1,handlefeedbacks.size()-msg.arg1);
            }
            return false;
        }
    });
    public void initData(){
        CommonApi.checkMyFeedback("",0,new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                MyFeedbackBean myFeedbackBean = (MyFeedbackBean)o;
                handlefeedbacks.clear();
//                handlefeedbacks.addAll();
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
        CommonApi.checkMyComment(type, commentId, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.e("MusicCheck","返回成功");
                MyCommentBean myCommentBean = (MyCommentBean)o;
                if(myCommentBean.getError()==0){
                    TextHelper.showText("操作成功");
                    Log.e("MusicCheck","操作成功");
                }
                handlefeedbacks.remove(positon);
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
