package com.GraduationDesign.MusicPlayer.ui.recommend;

import java.util.List;

public class WyRecommendListBean {

    /**
     * Code : OK
     * Body : [{"id":1409329655,"title":"最甜情歌 (女生版) ","author":"一玟",
     * "url":"https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=url&id=1409329655",
     * "pic":"https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=pic&id=1409329655",
     * "lrc":"https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=lrc&id=1409329655"},
     * {"id":1384026889,"title":"所念皆星河","author":"CMJ",
     * "url":"https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=url&id=1384026889",
     * "pic":"https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=pic&id=1384026889",
     * "lrc":"https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=lrc&id=1384026889"}
     * */

    private String Code;
    private List<BodyBean> Body;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public List<BodyBean> getBody() {
        return Body;
    }

    public void setBody(List<BodyBean> Body) {
        this.Body = Body;
    }

    public static class BodyBean {
        /**
         * id : 1409329655
         * title : 最甜情歌 (女生版)
         * author : 一玟
         * url : https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=url&id=1409329655
         * pic : https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=pic&id=1409329655
         * lrc : https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=lrc&id=1409329655
         */

        private int id;
        private String title;
        private String author;
        private String url;
        private String pic;
        private String lrc;

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
}
