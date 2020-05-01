package com.GraduationDesign.MusicPlayer.ui.settings.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class RegisterActivity extends AppCompatActivity {
    AutoCompleteTextView AtUserName,AtUserEmail;
    EditText AtUserPassword,AtUserPasswordAgain;
    Button Register_Button;
    private String SName,SEmail,SPassword,SRepeatPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AtUserName=findViewById(R.id.Register_name);
        AtUserEmail=findViewById(R.id.email);
        AtUserPassword=findViewById(R.id.Register_password);
        AtUserPasswordAgain=findViewById(R.id.Register_PPassword);
        Register_Button=findViewById(R.id.email_sign_in_button);
        Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goto_check()){
                    Register();
                }

            }
        });
    }
    private boolean goto_check(){
        SName = AtUserName.getText().toString();
        SEmail = AtUserEmail.getText().toString();
        SPassword = AtUserPassword.getText().toString();
        SRepeatPassword = AtUserPasswordAgain.getText().toString();
        if(SName.isEmpty()){
            TextHelper.showLongText("用户名不能为空");
            getCurrentFocus();
            return false;
        }
        if(!SEmail.contains("@")){
            TextHelper.showLongText("邮箱地址不合法，请重新输入");
            return false;
        }
        if(SPassword.length()<6){
            TextHelper.showLongText("密码过短，请重新输入");
            return false;
        }
        if(!SRepeatPassword.equals(SPassword)){
            TextHelper.showLongText("两次密码不一致，请核对后输入");
            return false;
        }
        return true;
    }
    private void Register(){
        CommonApi.RegisterToService(SName, SEmail, SPassword, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                LoginBean loginBean = (LoginBean)o;
                if(loginBean.getResult().get(0).getMessage().equals("success")){
                    SharedPreferences.Editor editor = getSharedPreferences("LoginMsg",Context.MODE_PRIVATE).edit();
                    editor.putString("UserName",loginBean.getResult().get(0).getUserName());
                    editor.putString("UserID",loginBean.getResult().get(0).getUserID());
                    editor.putString("UserEmail",loginBean.getResult().get(0).getUserEmail());
                    editor.putInt("UserIdentity",loginBean.getResult().get(0).getUserIdentity());
                    editor.apply();
                }
                mHandler.sendMessage(mHandler.obtainMessage(loginBean.error));
            }

            @Override
            public void onError(Exception e) {
                mHandler.sendMessage(mHandler.obtainMessage(ErrorCode.internet_waring));
            }
        });
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case ErrorCode.regiter_user_exist:
                    TextHelper.showLongText("用户邮箱已存在");
                    break;
                case ErrorCode.unknow_database_error:
                    TextHelper.showLongText("服务器错误");
                    break;
                case ErrorCode.internet_waring:
                    TextHelper.showLongText("网络错误");
                    break;
                case ErrorCode.regist_success:
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    RegisterActivity.this.finish();

            }
        }
    };

}
