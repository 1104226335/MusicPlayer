package com.GraduationDesign.MusicPlayer.ui.recommend.Adapter;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyMusicList;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetail {
    List<MyMusicList> musicLists ;
    String categoryName;
    public CategoryDetail(){
        musicLists = new ArrayList<>();
        categoryName = "null";
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setMusicLists(List<MyMusicList> musicLists) {
        this.musicLists = musicLists;
    }

    public List<MyMusicList> getMusicLists() {
        return musicLists;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
