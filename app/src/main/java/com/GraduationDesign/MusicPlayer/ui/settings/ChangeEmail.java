package com.GraduationDesign.MusicPlayer.ui.settings;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ErrorCode;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.LoginBean;
import com.GraduationDesign.MusicPlayer.ui.main.MainActivity;
import com.GraduationDesign.MusicPlayer.ui.settings.login.LoginActivity;
import com.GraduationDesign.MusicPlayer.utils.UserMessageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeEmail extends AppCompatActivity {

    @BindView(R.id.change_email_toolbar)
    Toolbar toolbar;
    @BindView(R.id.change_email_email)
    AutoCompleteTextView textEmail;
    @BindView(R.id.change_email_password)
    EditText textPassword;
    @BindView(R.id.email_change_email_button)
    Button changeEmail;
    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch(msg.what){
                case ErrorCode.change_email_DB_err:
                    TextHelper.showLongText("数据库错误");
                    break;
                case ErrorCode.change_email_exist:
                    TextHelper.showLongText("新邮箱已经注册过了");
                    break;
                case ErrorCode.change_email_over_login:
                    TextHelper.showLongText("登录已过期");
                    break;
                case ErrorCode.change_email_pass_wrong:
                    TextHelper.showLongText("密码错误");
                    break;
                case ErrorCode.change_email_success:
                    TextHelper.showLongText("修改成功");
                    finish();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        ButterKnife.bind(this);
        setToolbar();

    }

    @OnClick(R.id.email_change_email_button)
    public void goChangeEmail(){
        String newEmail = textEmail.getText().toString();
        String passWord = textPassword.getText().toString();
        if(UserMessageUtil.getInstance().isLogin())
            CommonApi.ChangeEmailToService(newEmail, UserMessageUtil.getInstance().getEmail(), passWord, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                LoginBean login = (LoginBean)o;
                LoginBean.ResultBean loginBean = login.getResult().get(0);
                if(loginBean.getMessage().equals("success")){
                    UserMessageUtil.getInstance().setEmail(loginBean.getUserEmail());
                }
                mhandler.sendMessage(mhandler.obtainMessage(login.error));
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
