package com.GraduationDesign.MusicPlayer.Web;

import android.util.Log;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyRecommendListBean;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by zhao on 2017/6/20.
 */

public class BaseApi {

    /**
     * post通用返回实体api
     * @param url
     * @param params
     * @param c 返回的实体类型
     * @param callback
     */
    protected static void postCommonApi(String url, Map<String, Object> params, final Class c, final ResultCallback callback) {
        HttpDataSource.httpPost(url, HttpUtil.makePostOutput(params), new JsonCallback() {
            @Override
            public void onFinish(JsonModel jsonModel) {
                if (jsonModel.isSuccess()) {
                    callback.onFinish(new Gson().fromJson(jsonModel.getResult(),c), jsonModel.getError());
                } else {
                    noSuccess(jsonModel,callback);
                }
            }
            @Override
            public void onError(Exception e) {
                error(e,callback);
            }
        });
    }
    protected static void postCommonEntity(String url, Map<String, Object> params, final Class c, final ResultCallback callback) {
        HttpDataSource.httpPost(url, HttpUtil.makePostOutput(params), new APICallBack() {
            @Override
            public void onFinish(String json) {
                callback.onFinish(new Gson().fromJson(json,c),1);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    /**
     * post通用返回字符串api
     * @param url
     * @param params
     * @param callback
     */
    protected static void postCommonReturnStringApi(String url, Map<String, Object> params, final ResultCallback callback) {
        HttpDataSource.httpPost(url, HttpUtil.makePostOutput(params), new JsonCallback() {
            @Override
            public void onFinish(JsonModel jsonModel) {
                if (jsonModel.isSuccess()) {
                    callback.onFinish(jsonModel.getResult(), jsonModel.getError());
                }else {
                    noSuccess(jsonModel,callback);
                }
            }
            @Override
            public void onError(Exception e) {
                error(e,callback);
            }
        });
    }

    /**
     * get通用返回实体api
     * @param url
     * @param params
     * @param callback
     */
    protected static void getCommonApi(String url, Map<String, Object> params,  final ResultCallback callback) {
        HttpDataSource.httpGet(HttpUtil.makeURL(url,params), new mlweiCallback() {
            @Override
            public void onFinish(WyRecommendListBean jsonModel) {
                if (jsonModel.getCode().equals("OK")) {
                    callback.onFinish(jsonModel, 0);
                } else {
//                    noSuccess(jsonModel,callback);
                }
            }

            @Override
            public void onError(Exception e) {
               error(e,callback);
            }
        });
    }
    protected static void getEntityApi(String url, Map<String, Object> params, final Class c, final ResultCallback callback) {
        HttpDataSource.httpGetJson(HttpUtil.makeURL(url,params),new APICallBack(){
            @Override
            public void onFinish(String jsonModel) {
                Log.d("Http", "getEntityApi：" + jsonModel);
               callback.onFinish(new Gson().fromJson(jsonModel, c),1);

            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }


    /**
     * get通用返回Html字符串api
     * @param url
     * @param params
     * @param callback
     */
    protected static void getCommonReturnHtmlStringApi(String url, Map<String, Object> params, String charsetName, final ResultCallback callback) {
        HttpDataSource.httpGet_html(HttpUtil.makeURL(url, params), charsetName, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o,code);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
//                error(e,callback);
            }
        });
    }


    /**
     * get通用获取实体列表api
     * @param url
     * @param params
     * @param c 返回是列表实体类型
     * @param callback
     */
    protected static void getCommonListApi(String url, Map<String, Object> params, final Class c, final ResultCallback callback) {
        HttpDataSource.httpGet(HttpUtil.makeURL(url,params), new mlweiCallback() {
            @Override
            public void onFinish(WyRecommendListBean jsonModel) {
                if (jsonModel.getCode().equals("OK")) {
                    try {
                        callback.onFinish(jsonModel, 0);
                    }catch (Exception e){
                        callback.onError(e);
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onError(Exception e) {
               error(e,callback);
            }
        });
    }

    /**
     * api异常处理
     * @param e
     * @param callback
     */
    private static void error(Exception e, final ResultCallback callback){
      /*  if (e.toString().contains("SocketTimeoutException") || e.toString().contains("UnknownHostException")) {
            TextHelper.showText("网络连接超时，请检查网络");
        }*/
        e.printStackTrace();
        callback.onError(e);
    }

    /**
     * api请求失败处理
     * @param jsonModel
     * @param callback
     */
    private static void noSuccess(JsonModel jsonModel, ResultCallback callback){
        if (!jsonModel.isSuccess()) {
            if (jsonModel.getError() == ErrorCode.no_security) {
                TextHelper.showText("登录过期，请重新登录");

            } else {
                if (jsonModel.getError() == 0) {
                    callback.onFinish(jsonModel.getResult(), -1);
                } else {
                    callback.onFinish(jsonModel.getResult(), jsonModel.getError());
                }
            }
        }
    }
}
