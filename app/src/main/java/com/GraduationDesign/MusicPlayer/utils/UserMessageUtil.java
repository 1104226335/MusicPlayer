package com.GraduationDesign.MusicPlayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.GraduationDesign.MusicPlayer.MusicPlayerApplication;

public class UserMessageUtil {
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;

    public static String UserMessageName = "LoginMsg";
    public static String Name = "UserName";
    public static String Email = "UserEmail";
    public static String Identity = "UserIdentity";
    public static String IsLogin = "IsLogin";
    public static String ID = "UserID";
    public static String Pic = "UserPic";

    public static int IDENTITY_USER = 0;
    public static int IDENTITY_ADMIN= 1;


    private static class UserMessageHolder{
        private static UserMessageUtil userMessageUtil = new UserMessageUtil();
    }
    public UserMessageUtil(){
        sharedPreferences = MusicPlayerApplication.getInstance().getSharedPreferences(UserMessageUtil.UserMessageName,Context.MODE_PRIVATE);
    }
    public static UserMessageUtil getInstance(){
        return UserMessageHolder.userMessageUtil;
    }
    public String getName(){
        return sharedPreferences.getString(UserMessageUtil.Name,"登录");
    }
    public String getEmail(){
        return sharedPreferences.getString(UserMessageUtil.Email,"");
    }
    public int getIdentity(){
        return sharedPreferences.getInt(UserMessageUtil.Identity,UserMessageUtil.IDENTITY_USER);
    }
    public String getID(){
        return sharedPreferences.getString(UserMessageUtil.ID,"");
    }
    public boolean isLogin(){
        return sharedPreferences.getBoolean(UserMessageUtil.IsLogin,false);
    }

    public  String getPic() {
        return sharedPreferences.getString(UserMessageUtil.Pic,"");
    }



    public  void setAll(String name, String email, String ID,String pic, int identity, boolean isLogin) {
        editor = sharedPreferences.edit();
        editor.putString(UserMessageUtil.Name,name);
        editor.putString(UserMessageUtil.Email,email);
        editor.putString(UserMessageUtil.ID,ID);
        editor.putString(UserMessageUtil.Pic,pic);
        editor.putInt(UserMessageUtil.Identity,identity);
        editor.putBoolean(UserMessageUtil.IsLogin,isLogin);
        editor.apply();
    }
    public  void setPic(String pic) {
        editor = sharedPreferences.edit();
        editor.putString(UserMessageUtil.Pic,pic);
        editor.apply();
    }
    public  void setName(String name) {
        editor = sharedPreferences.edit();
        editor.putString(UserMessageUtil.Name,name);
        editor.apply();
    }

    public  void setEmail(String email) {
        editor = sharedPreferences.edit();
        editor.putString(UserMessageUtil.Email,email);
        editor.apply();
    }

    public  void setID(String ID) {
        editor = sharedPreferences.edit();
        editor.putString(UserMessageUtil.ID,ID);
        editor.apply();
    }

    public  void setIdentity(int identity) {
        editor = sharedPreferences.edit();
        editor.putInt(UserMessageUtil.Identity,identity);
        editor.apply();
    }

    public  void setIsLogin(boolean isLogin) {
        editor = sharedPreferences.edit();
        editor.putBoolean(UserMessageUtil.IsLogin,isLogin);
        editor.apply();
    }
    public void deleteAll(){
        editor = sharedPreferences.edit();
        editor.putBoolean(UserMessageUtil.IsLogin,false);
        editor.putString(UserMessageUtil.Name,"登陆");
        editor.putString(UserMessageUtil.ID,"");
        editor.putString(UserMessageUtil.Email,"");
        editor.putInt(UserMessageUtil.Identity,UserMessageUtil.IDENTITY_USER);
        editor.putString(UserMessageUtil.Pic,"");
        editor.apply();
    }

}
