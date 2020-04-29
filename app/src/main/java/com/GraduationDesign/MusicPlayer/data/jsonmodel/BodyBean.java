package com.GraduationDesign.MusicPlayer.data.jsonmodel;

public class BodyBean {
    /**
     * id : 411214279
     * title : 雅俗共赏
     * author : 许嵩
     * url : https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=url&id=411214279
     * pic : https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=pic&id=411214279
     * lrc : https://api88.net/api/netease/?key=e7db13d8a95048943afd37dba0e57794&cache=1&type=lrc&id=411214279
     */

    public int id;
    public String title;
    public String author;
    public String url;
    public String pic;
    public String lrc;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }
}
