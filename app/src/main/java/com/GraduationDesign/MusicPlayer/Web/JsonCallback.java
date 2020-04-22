package com.GraduationDesign.MusicPlayer.Web;




/**
 * Created by zhao on 2016/10/25.
 */

public interface JsonCallback {

    void onFinish(JsonModel jsonModel);

    void onError(Exception e);

}
