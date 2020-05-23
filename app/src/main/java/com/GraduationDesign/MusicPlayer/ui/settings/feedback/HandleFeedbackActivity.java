package com.GraduationDesign.MusicPlayer.ui.settings.feedback;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyFeedbackBean;
import com.GraduationDesign.MusicPlayer.utils.UserMessageUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandleFeedbackActivity extends AppCompatActivity implements HandleFeedbackAdapter.OnAction{
    @BindView(R.id.app_handle_feedback_setting_toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.rv_handle_feedback)
    RecyclerView handle_feedback;


    HandleFeedbackAdapter adapter;
    List<MyFeedbackBean.ResultBean> handlefeedbacks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_feedback);
        ButterKnife.bind(this);
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
            getSupportActionBar().setTitle("消息通知");
            if(UserMessageUtil.getInstance().isLogin()){
                if(UserMessageUtil.getInstance().getIdentity() == UserMessageUtil.IDENTITY_ADMIN){
                    getSupportActionBar().setTitle("处理反馈");
                }
            }
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
        SharedPreferences shared = getSharedPreferences("LoginMsg",Context.MODE_PRIVATE);
        int userIdentity = shared.getInt("UserIdentity",0);
        boolean islogin = shared.getBoolean("IsLogin",false);

        if(islogin){
            if(userIdentity == 1){
                CommonApi.checkMyFeedback("check",0,"",new ResultCallback() {
                    @Override
                    public void onFinish(Object o, int code) {
                        MyFeedbackBean myFeedbackBean = (MyFeedbackBean)o;
                        handlefeedbacks.clear();
                        handlefeedbacks.addAll(myFeedbackBean.getResult());
                        mhandler.sendMessage(mhandler.obtainMessage(1));
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }else {
                String email = shared.getString("UserEmail","error");
                CommonApi.checkMyFeedback("me",0,email,new ResultCallback() {
                    @Override
                    public void onFinish(Object o, int code) {
                        MyFeedbackBean myFeedbackBean = (MyFeedbackBean)o;
                        handlefeedbacks.clear();
                        handlefeedbacks.addAll(myFeedbackBean.getResult());
                        mhandler.sendMessage(mhandler.obtainMessage(1));
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        }

    }

    @Override
    public void onAction(View v, final int feedbackId, final int position) {
        PopupMenu actionMenu = new PopupMenu(this, v, Gravity.END | Gravity.BOTTOM);
        actionMenu.inflate(R.menu.check_action);
        actionMenu.getMenu().findItem(R.id.menu_item_pass).setVisible(false);
        actionMenu.getMenu().findItem(R.id.menu_item_failed_pass).setVisible(false);
        actionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_feedback:
                        feedback(feedbackId,position);
                        break;
                }
                return true;
            }
        });
        actionMenu.show();
    }
    public void feedback(final int feedbackId, final int positon){

        feedbackDialogFragment.newInstance()
                .setTitle("回复反馈")
                .setCallback(new feedbackDialogFragment.Callback() {
                    @Override
                    public void onConfirm(String content) {
                        sendConfirm(content,feedbackId,positon);
                    }
                })
                .show(getSupportFragmentManager(), "sendFeedback");

    }

    public void sendConfirm(String content,int feedbackId, final int positon) {
        CommonApi.checkMyFeedback("pass", feedbackId,content, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.e("MusicCheck","返回成功");
                MyFeedbackBean myFeedbackBean = (MyFeedbackBean)o;
                if(myFeedbackBean.getError()==0){
                    TextHelper.showText("回复成功");
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
