package com.GraduationDesign.MusicPlayer.Web;

import java.util.HashMap;
import java.util.Map;

import com.GraduationDesign.MusicPlayer.ui.recommend.WyRecommendListBean;
import com.GraduationDesign.MusicPlayer.utils.StringHelper;

/**
 * Created by zhao on 2017/7/24.
 */

public class CommonApi extends BaseApi{


    public static void RegisterToService(String Username,String Useremail,String password,final ResultCallback callback){
        Map<String,Object> register = new HashMap<>();
        register.put("UserID",StringHelper.getStringRandom(10));
        register.put("username",Username);
        register.put("UserEmail",Useremail);
        register.put("password",password);
        postCommonReturnStringApi(URLCONST.method_RegisterToService, register, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish((String)o,code);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });

    }
    public static void logintoservice(String email,String password,final ResultCallback callback){
        Map<String,Object> login = new HashMap<>();
        login.put("UserEmail",email);
        login.put("UserPassword",password);
        postCommonReturnStringApi(URLCONST.method_loginToService, login, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish((String)o,code);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });

    }
    public static void getWyRecommendById(String Id,String key,final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("key",key);
        param.put("cache",1);
        param.put("type","songlist");
        param.put("id",Id);
        getCommonApi(URLCONST.Wy_List_Api, param,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
