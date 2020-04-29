package com.GraduationDesign.MusicPlayer.Web;

import java.util.HashMap;
import java.util.Map;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyComment;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyRecommendListBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WySearchResult;
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
        getEntityApi(URLCONST.Wy_List_Api, param,WyRecommendListBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    /**
     *      http://music.163.com/api/v1/resource/comments/R_SO_4_{歌曲ID}?limit=20&offset=0
     * @param Id       歌曲ID
     * @param num       limit=：返回数据条数(每页获取的数量)，默认为20，可以自行更改
     * @param offset    offset：偏移量(翻页)，offset需要是limit的倍数
     * @param callback  callback
     */

    public static void getWyComment(String Id,int num,int offset,final ResultCallback callback){

        Map<String,Object> param = new HashMap<>();
        param.put("limit",num);
        param.put("offset",offset);
        getEntityApi(URLCONST.Wy_comment_Api+Id,param, WyComment.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    /**
     * @param userId 用户Id
     * @param callback callback
     */
    public static void sendMyComment(String userId,String musicid,String date,String content,final ResultCallback callback){

        Map<String,Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("date",date);
        param.put("content",content);
        param.put("musicid",musicid);
        postCommonReturnStringApi(URLCONST.method_sendmycomment, param, new ResultCallback() {
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

    public static void getWySearch(String value,String key,final ResultCallback callback){
        String id = StringHelper.urlEncodeChinese(value);
        Map<String,Object> param = new HashMap<>();
        param.put("key",key);
        param.put("cache",1);
        param.put("type","so");
        param.put("id",id);
        param.put("nu",30);
        getEntityApi(URLCONST.Wy_List_Api, param,WySearchResult.class,  new ResultCallback() {
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
