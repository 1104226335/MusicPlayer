package com.GraduationDesign.MusicPlayer.ui.settings;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ErrorCode;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.LoginBean;
import com.GraduationDesign.MusicPlayer.utils.UserMessageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassword extends AppCompatActivity {
    @BindView(R.id.change_password_toolbar)
    Toolbar toolbar;
    @BindView(R.id.change_password_old)
    EditText oldPassWord;
    @BindView(R.id.change_password_newP)
    EditText newPassWord;
    @BindView(R.id.change_password_again)
    EditText againPassWord;
    @BindView(R.id.change_password_button)
    Button changePassword;
    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch(msg.what){
                case ErrorCode.change_password_DB_err:
                    TextHelper.showLongText("数据库错误");
                    break;
                case ErrorCode.change_password_over_login:
                    TextHelper.showLongText("登录已过期");
                    break;
                case ErrorCode.change_password_pass_wrong:
                    TextHelper.showLongText("密码错误");
                    break;
                case ErrorCode.change_password_success:
                    TextHelper.showLongText("修改成功");
                    finish();
                    break;
                case ErrorCode.internet_waring:
                    TextHelper.showLongText("网络错误");
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        setToolbar();
    }

    @OnClick(R.id.change_password_button)
    public void changePassword(){
        String oldPass = oldPassWord.getText().toString();
        String newPass = newPassWord.getText().toString();
        String againPass = againPassWord.getText().toString();
        if(againPass.equals(newPass)){
            CommonApi.ChangePassWordToService(UserMessageUtil.getInstance().getEmail(),
                    newPass, oldPass, new ResultCallback() {
                        @Override
                        public void onFinish(Object o, int code) {
                            LoginBean login = (LoginBean)o;
                            mhandler.sendMessage(mhandler.obtainMessage(login.error));
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
        }else {
            TextHelper.showLongText("两次输入的密码不一致");
        }
    }

    public void setToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("修改邮箱");
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
