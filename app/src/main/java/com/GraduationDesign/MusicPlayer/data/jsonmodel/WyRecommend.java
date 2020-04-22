package com.GraduationDesign.MusicPlayer.data.jsonmodel;

public class WyRecommend {
    private String coverUrl;
    private String name;
    private String id;

    public WyRecommend(){

    }
    public String getCoverUrl() {
        return coverUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
