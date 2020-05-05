package com.GraduationDesign.MusicPlayer.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.ui.base.BaseFragment;
import com.GraduationDesign.MusicPlayer.ui.settings.checkcomment.CheckCommentActivity;
import com.GraduationDesign.MusicPlayer.ui.settings.feedback.HandleFeedbackActivity;
import com.GraduationDesign.MusicPlayer.ui.settings.feedback.SubmitFeedbackActivity;
import com.GraduationDesign.MusicPlayer.ui.settings.login.LoginActivity;
import com.GraduationDesign.MusicPlayer.utils.UpdateHelper;

import java.io.IOException;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/1/16
 * Time: 9:59 PM
 * Desc: SettingsFragment
 */
public class SettingsFragment extends BaseFragment {

    @BindView(R.id.login)
    RelativeLayout login;
    @BindView(R.id.music_play_setting)
    RelativeLayout musicplay_setting;
    @BindView(R.id.setting_app_about)
    RelativeLayout app_about;
    @BindView(R.id.setting_check_comment)
    RelativeLayout app_check_comment;
    @BindView(R.id.setting_user_name)
    TextView tvUserName;
    @BindView(R.id.logout)
    RelativeLayout logout;
    @BindView(R.id.submit_feedback)
    RelativeLayout setting_submit_feedback;
    @BindView(R.id.handle_feedback)
    RelativeLayout setting_handle_feedback;
    @BindView(R.id.tv_handle_feedback)
    TextView userMessage;
    Unbinder unbinder;


    SharedPreferences shared;
    String userName;
    int userIdentity;
    boolean islogin;
    int VersionCode = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);

        shared = getContext().getSharedPreferences("LoginMsg",Context.MODE_PRIVATE);
        userName = shared.getString("UserName","登录");
        userIdentity = shared.getInt("UserIdentity",0);
        tvUserName.setText(userName);
        musicplay_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MusicPlaySetting.class);
                startActivity(intent);
            }
        });
        setting_submit_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SubmitFeedbackActivity.class);
                startActivity(intent);
            }
        });
        setting_handle_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HandleFeedbackActivity.class);
                startActivity(intent);
            }
        });

    }
    @OnClick(R.id.setting_app_about)
    public void about(){
        checkUpdate();
    }

    @OnClick(R.id.login)
    public void login(){
        islogin = shared.getBoolean("IsLogin",false);
        if(islogin){
            startActivity(new Intent(getActivity(),AccountBindActivitity.class));
        }else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
    @OnClick(R.id.logout)
    public void logout(){
        islogin = shared.getBoolean("IsLogin",false);
        if(islogin){
            SharedPreferences.Editor editor = shared.edit();
            editor.putBoolean("IsLogin",false);
            editor.putString("UserName","登陆");
            editor.putString("UseID","");
            editor.putString("UserEmail","");
            editor.putInt("UserIdentity",0);
            editor.apply();
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        }else {

        }


    }
    @OnClick(R.id.setting_check_comment)
    public void checkComment(){
        Intent intent = new Intent(getActivity(),CheckCommentActivity.class);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        userName = shared.getString("UserName","登录");
        tvUserName.setText(userName);
        if(userIdentity==0){
            app_check_comment.setVisibility(View.GONE);
            setting_submit_feedback.setVisibility(View.VISIBLE);
            userMessage.setText("消息");
        }else {
            app_check_comment.setVisibility(View.VISIBLE);
            setting_submit_feedback.setVisibility(View.GONE);
            userMessage.setText("处理反馈");
        }
        islogin = shared.getBoolean("IsLogin",false);
        if(islogin){
            logout.setVisibility(View.VISIBLE);
        }else logout.setVisibility(View.GONE);

    }
    public  void checkUpdate() {
//        Activity context = this;
        float versionCode = VersionCode;
        String url = "https://www.baiqing.work/apk/TheMusic.apk";
        boolean isForced = false;
        String versionName = getVersionName();
        if( versionName != null){
            if (versionCode > Float.parseFloat(versionName)) {
                String downLoadPath = getActivity().getExternalFilesDir(null).toString() + "/download/";
                String newFileName = "TheMusic"+versionCode+".apk";
                String oldFileName = "TheMusic"+Float.parseFloat(versionName)+".apk";

                //设置参数
                UpdateHelper.getInstance()
                        .setContext(getActivity())
                        .setDownloadPath(downLoadPath)
                        .setNewFileName(newFileName)
                        .setOldFileName(oldFileName)
                        .setUrl(url)
                        .setIsforce(isForced)
                        .start();
            }else {
                TextHelper.showLongText("已经是最新版本");
            }
        }
    }
    public void getAsyn(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                VersionCode = 0;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String result = response.body().string();
                    VersionCode = Integer.parseInt(result);
                }
            }
        });
    }
    private String getVersionName()
    {
        // 获取packagemanager的实例
        PackageManager packageManager = getActivity().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(getActivity().getPackageName(),0);
            String versionName = packInfo.versionName;
            int versionCode = packInfo.versionCode;
            return Integer.toString(versionCode);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
