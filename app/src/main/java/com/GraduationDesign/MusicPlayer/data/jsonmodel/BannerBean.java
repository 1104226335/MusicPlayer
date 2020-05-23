package com.GraduationDesign.MusicPlayer.data.jsonmodel;

import java.util.ArrayList;
import java.util.List;

public class BannerBean {
    List<String>   bannerPic = new ArrayList<>();
    List<String> bannerMusicId = new ArrayList<>();

    public BannerBean(){

    }


    public void setBannerPic(List<String> bannerPic) {
        this.bannerPic = bannerPic;
    }

    public void setBannerMusicId(List<String> bannerMusicId) {
        this.bannerMusicId = bannerMusicId;
    }

    public List<String> getBannerMusicId() {
        return bannerMusicId;
    }


    public List<String> getBannerPic() {
        return bannerPic;
    }
}
