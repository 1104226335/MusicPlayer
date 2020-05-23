package com.GraduationDesign.MusicPlayer.Web;

import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.BannerBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.BodyBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.LoginBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyCommentBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyFeedbackBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyMusicList;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.UploadMusicBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyComment;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyRecommendListBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WySearchResult;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.ui.recommend.Adapter.CategoryDetail;
import com.GraduationDesign.MusicPlayer.utils.BitmapUtil;
import com.GraduationDesign.MusicPlayer.utils.StringHelper;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;

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
        postCommonEntity(URLCONST.method_RegisterToService, register,LoginBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
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
        postCommonEntity(URLCONST.method_loginToService, login,LoginBean.class, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });

    }

    public static void ChangeEmailToService(String newEmail,String oldEmail,String password,final ResultCallback callback){
        Map<String,Object> register = new HashMap<>();
        register.put("newEmail",newEmail);
        register.put("oldEmail",oldEmail);
        register.put("password",password);
        postCommonEntity(URLCONST.method_ChangeEmailToService, register,LoginBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });

    }
    public static void ChangePassWordToService(String Email,String newPass,String oldPass,final ResultCallback callback){
        Map<String,Object> register = new HashMap<>();
        register.put("Email",Email);
        register.put("newPass",newPass);
        register.put("oldPass",oldPass);
        postCommonEntity(URLCONST.method_ChangePassToService, register,LoginBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
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
                Log.d("Http", "getWyComment：" + o.toString());
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    /**
     * @param userEmail 用户Email
     * @param callback callback
     */
    public static void sendMyComment(String userEmail,String musicid,String content,final ResultCallback callback){
        String date = TimeHelper.getStringDateForKey("yyyy-MM-dd HH:mm:ss");
        Map<String,Object> param = new HashMap<>();
        param.put("userEmail",userEmail);
        param.put("date",date);
        param.put("content",content);
        param.put("musicId",musicid);
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
                Log.d("Http", "getWySearch：" + o);
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public static void getMyRecommend(String Email,final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("Email",Email);
        getEntityApi(URLCONST.method_getRecommend, param,CategoryDetail.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.d("Http", "getMyRecommend：" + o);
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public static void updateMyRecommend(String Email,final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("Email",Email);
        postCommonReturnStringApi(URLCONST.method_updateRecommend, param, new ResultCallback() {
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
    public static void sendHistory(String Email,String listId,final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("Email",Email);
        param.put("listId",listId);
        postCommonReturnStringApi(URLCONST.method_sendHistory, param, new ResultCallback() {
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
    public static void sendMusicList(MyMusicList myMusicList, final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("listPic",myMusicList.getImageUrl());
        param.put("listName",myMusicList.getListName());
        param.put("listId",myMusicList.getId());
        postCommonReturnStringApi(URLCONST.method_sendMusicList, param, new ResultCallback() {
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
    /**
     * @param callback callback
     */
    public static void checkMyComment(String type,String commentId,final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("type",type);
        param.put("code",commentId);
        getEntityApi(URLCONST.method_checkmycomment, param,MyCommentBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.d("Http", "checkMyComment：" + o);
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    /**
     * @param callback callback
     */
    public static void sendMyFeedback(String UserEmail,String feedbackContent,final ResultCallback callback){
        String date = TimeHelper.getStringDateForKey("yyyy-MM-dd HH:mm:ss");
        Map<String,Object> param = new HashMap<>();
        param.put("email",UserEmail);
        param.put("content",feedbackContent);
        param.put("time",date);
        postCommonReturnStringApi(URLCONST.method_sendmyfeedback, param, new ResultCallback() {
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
    /**
     * @param callback callback
     */
    public static void checkMyFeedback(String type,int code,String feedback,final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("type",type);
        param.put("code",code);
        param.put("feed",feedback);
        getEntityApi(URLCONST.method_checkmyfeedback, param,MyFeedbackBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.d("Http", "checkMyComment：" + o);
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    /**
     * @param callback callback
     */
    public static void UploadMusicFile(Song file, String userEmail, final ResultCallback callback,final UIProgressRequestListener uiProgressRequestListener){
        Map<String,Object> param = new HashMap<>();
        param.put("userEmail",userEmail);
        param.put("Title",file.getTitle());
        param.put("Album",file.getAlbum());
        param.put("Artist",file.getArtist());
        param.put("DisplayName",file.getDisplayName());
        param.put("Size",file.getSize());
        param.put("Duration",file.getDuration());
        postUploadApi(URLCONST.method_upLoadMusic,new File(file.getPath()), param,JsonModel.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.d("Http", "uploadFile：" + o);
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        },uiProgressRequestListener);
    }
    /**
     * @param callback callback
     */
    public static void checkMyUpload(String type,int musicId,final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("type",type);
        param.put("code",musicId);
        getEntityApi(URLCONST.method_checkLoadMusic, param,UploadMusicBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.d("Http", "checkMyComment：" + o);
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    /**
     * @param callback callback
     */
    public static void UploadPicFile(String path, String userEmail, final ResultCallback callback,final UIProgressRequestListener uiProgressRequestListener){
        String compress = BitmapUtil.compressImage(path);
        Map<String,Object> param = new HashMap<>();
        param.put("userEmail",userEmail);
        postUploadApi(URLCONST.method_upLoadPic,new File(compress), param,JsonModel.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.d("Http", "uploadFile：" + o);
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        },uiProgressRequestListener);
    }
    public static void getBanner(String Email,final ResultCallback callback){
        Map<String,Object> param = new HashMap<>();
        param.put("Email",Email);
        getEntityApi(URLCONST.method_getBanner, param,BannerBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public static void getWyMusic(String Id,String key,final ResultCallback callback){

        Map<String,Object> param = new HashMap<>();
        param.put("key",key);
        param.put("id",Id);
        param.put("type","song");
        param.put("cache",1);
        getEntityApi(URLCONST.Wy_List_Api,param, BodyBean.class,  new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.d("Http", "getWyMusic：" + o.toString());
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

}
