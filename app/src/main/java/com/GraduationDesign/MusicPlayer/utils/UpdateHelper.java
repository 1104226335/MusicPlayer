package com.GraduationDesign.MusicPlayer.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by minmin.shen
 * on 2019/1/23
 * 检查更新版本工具类
 */
public class UpdateHelper {
    private String downLoadPath = "";
    private String url = "";//apk下载地址
    private boolean isforce = false;
    private String oldApkName = "";
    private String newApkName = "";
    private Activity context;

    private BaseDialog updataDialog;
    private BaseDialog installDialog;
    private ProgressDialog progressDialog;
    private FragmentManager fm;
    public static UpdateHelper updateHelper;

    public static UpdateHelper getInstance() {
        if (updateHelper == null) {
            updateHelper = new UpdateHelper();
        }
        return updateHelper;
    }

    public void start(){
        if(checkIsHaveNewAPK()){
            showInstallDialog("发现新版本，是否安装?");
        }else{
            showUpdataDialog();
        }
    }

    /**
     * 版本更新提示框
     */
    public void showUpdataDialog() {
        updataDialog = BaseDialog.getInstance(context)
                .setContent("发现新版本，是否更新?")
                .setSelectText("更新","取消" , new BaseDialogListener() {
                    @Override
                    public void onPositionText() {
                        updataDialog.dismiss();
                        createProgress(context);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                downloadApk();
                            }
                        }).start();
                    }

                    @Override
                    public void onNegativeText() {
                        updataDialog.dismiss();
                        if (isforce) {
                            System.exit(0);
                        }
                    }
                });
        updataDialog.show();
    }

    /**
     * 弹出安装Dialog
     */
    private void showInstallDialog(String content){
        installDialog = BaseDialog.getInstance(context)
                .setContent(content)
                .setSelectText("安装","取消" , new BaseDialogListener() {
                    @Override
                    public void onPositionText() {
                        installDialog.dismiss();
                        File fileAPK = new File(downLoadPath, newApkName);
                        installApk(context, fileAPK);
                    }

                    @Override
                    public void onNegativeText() {
                        installDialog.dismiss();
                        if (isforce) {
                            System.exit(0);
                        }
                    }
                });
        installDialog.show();
    }

    /**
     * 检查有没有最新的APK
     */
    private boolean checkIsHaveNewAPK(){
        File file = new File(downLoadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File fileAPK = new File(downLoadPath, newApkName);
        if(fileAPK.exists()){
            return true;
        }
        return false;
    }

    /**
     * 删除旧的apk
     */
    private void delOldApk(){
        File fileAPK = new File(downLoadPath, oldApkName);
        if(fileAPK.exists()){
            fileAPK.delete();
        }
    }

    /**
     * 下载APK
     */
    private void downloadApk() {
        try {
            final long startTime = System.currentTimeMillis();
            Log.i("DOWNLOAD", "startTime=" + startTime);
            //获取文件名
            URL myURL = new URL(url);
            URLConnection conn = myURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            int fileSize = conn.getContentLength();//根据响应获取文件大小
            Log.i("DOWNLOAD", "总大小="+fileSize);
            if (fileSize <= 0) throw new RuntimeException("无法获知文件大小 ");
            if (is == null) throw new RuntimeException("stream is null");
            File file1 = new File(downLoadPath);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            //把数据存入路径+文件名
            FileOutputStream fos = new FileOutputStream(downLoadPath + "/" + newApkName);
            byte buf[] = new byte[1024];
            int downLoadFileSize = 0;
            do {
                //循环读取
                int numread = is.read(buf);
                if (numread == -1) {
                    break;
                }
                fos.write(buf, 0, numread);
                downLoadFileSize += numread;
                Log.i("DOWNLOAD", "downLoadFileSize="+downLoadFileSize);
                Log.i("DOWNLOAD", "fileSize="+fileSize);
                Log.i("DOWNLOAD", "download downLoadFileSize="+(int)((float)downLoadFileSize*100/fileSize));
                progressDialog.setProgress((int)((float)downLoadFileSize*100/fileSize));
                //更新进度条
            } while (true);
//            delOldApk();

            progressDialog.dismiss();
            Looper.prepare();
            showInstallDialog("下载完成，是否安装？");
            Looper.loop();
            Log.i("DOWNLOAD", "download success");
            Log.i("DOWNLOAD", "totalTime=" + (System.currentTimeMillis() - startTime));
            is.close();
        } catch (Exception ex) {
            Log.e("DOWNLOAD", "error: " + ex.getMessage(), ex);
        }
    }

    /**
     * 强制更新时显示在屏幕的进度条
     */
    private void createProgress(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在下载...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    /**
     * 安装apk
     */
    private void installApk(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(context, "com.GraduationDesign.MusicPlayer.fileProvider", file);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    /**
     * apk网络地址
     */
    public UpdateHelper setContext(Activity context) {
        this.context = context;
        return this;
    }

    /**
     * apk网络地址
     */
    public UpdateHelper setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 新版本文件名
     */
    public UpdateHelper setNewFileName(String fileName) {
        this.newApkName = fileName;
        return this;
    }

    /**
     * 旧版本文件名
     */
    public UpdateHelper setOldFileName(String fileName) {
        this.oldApkName = fileName;
        return this;
    }

    /**
     * Fragment管理器
     */
    public UpdateHelper setFragmentManager(FragmentManager fm) {
        this.fm = fm;
        return this;
    }

    /**
     * 下载路径
     */
    public UpdateHelper setDownloadPath(String downLoadPath) {
        this.downLoadPath = downLoadPath;
        return this;
    }

    /**
     * 是否强制更新
     */
    public UpdateHelper setIsforce(boolean isforce) {
        this.isforce = isforce;
        return this;
    }

}
