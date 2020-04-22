package com.GraduationDesign.MusicPlayer.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.IOException;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.utils.BaseDialog;
import com.GraduationDesign.MusicPlayer.utils.UpdateHelper;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppAboutActivity extends AppCompatActivity {
    int VersionCode = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_about);
        getAsyn("https://www.baiqing.work/apk/MusicUpdate.php");
        RelativeLayout relativeLayout = findViewById(R.id.app_check_update);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUpdate();
            }
        });
    }
    public  void checkUpdate() {
        Activity context = this;
        float versionCode = VersionCode;
        String url = "https://www.baiqing.work/apk/TheMusic.apk";
        boolean isForced = false;
        String versionName = getVersionName();
        if( versionName != null){
            if (versionCode > Float.parseFloat(versionName)) {
                String downLoadPath = context.getExternalFilesDir(null).toString() + "/download/";
                String newFileName = "TheMusic"+versionCode+".apk";
                String oldFileName = "TheMusic"+Float.parseFloat(versionName)+".apk";

                //设置参数
                UpdateHelper.getInstance()
                        .setContext(context)
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
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
            String versionName = packInfo.versionName;
            int versionCode = packInfo.versionCode;
            return Integer.toString(versionCode);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return "";
    }


}
