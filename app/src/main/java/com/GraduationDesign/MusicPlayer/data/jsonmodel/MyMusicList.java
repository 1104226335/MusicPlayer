package com.GraduationDesign.MusicPlayer.data.jsonmodel;

public class MyMusicList {

    public  String imageUrl;
    public String listName;
    public String id;

    public MyMusicList(){

    }
    public MyMusicList(String coverUrl,String name,String id){
        this.imageUrl = coverUrl;
        this.listName = name;
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getListName() {
        return listName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
