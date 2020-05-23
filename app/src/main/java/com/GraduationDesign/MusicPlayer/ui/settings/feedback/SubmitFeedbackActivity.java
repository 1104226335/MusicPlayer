package com.GraduationDesign.MusicPlayer.ui.settings.feedback;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ErrorCode;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.utils.UserMessageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubmitFeedbackActivity extends AppCompatActivity {
    @BindView(R.id.app_submit_feedback_setting_toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_submit_feedback)
    EditText EditText_feedback;
    @BindView(R.id.button_submit_feedback)
    Button submit_feedback_button;

    String feedback;
    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    break;
                case ErrorCode.sendComment_success:
                    TextHelper.showLongText("反馈成功");
                    EditText_feedback.setText("");
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_feedback);
        ButterKnife.bind(this);
        setToolbar();
    }
    @OnClick(R.id.button_submit_feedback)
    public void subbmit(){
        feedback = EditText_feedback.getText().toString();
        if(TextUtils.isEmpty(feedback)){
            TextHelper.showLongText("反馈为空");

        }else sendfeedback(feedback);
    }
    public void sendfeedback(String feedback){
        SharedPreferences shared = getSharedPreferences("LoginMsg",Context.MODE_PRIVATE);
        String useremail = shared.getString("UserEmail","error");
        CommonApi.sendMyFeedback(useremail, feedback, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                mhandler.sendMessage(mhandler.obtainMessage(code));
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void setToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("帮助与反馈");
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
}
