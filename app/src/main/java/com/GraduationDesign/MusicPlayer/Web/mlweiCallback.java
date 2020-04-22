package com.GraduationDesign.MusicPlayer.Web;

import com.GraduationDesign.MusicPlayer.ui.recommend.WyRecommendListBean;

public interface mlweiCallback {
    void onFinish(WyRecommendListBean jsonModel);

    void onError(Exception e);

}
