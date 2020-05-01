package com.GraduationDesign.MusicPlayer.Web;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyRecommendListBean;

public interface mlweiCallback {
    void onFinish(WyRecommendListBean jsonModel);

    void onError(Exception e);

}
