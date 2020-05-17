package com.GraduationDesign.MusicPlayer.data.jsonmodel;

import java.util.List;

public class MyMusicBean {

    /**
     * error : 0
     * success : true
     * result : [{"UserEmail":"123@qq.com","filepath":"1234","MusicId":"1","Title":null,"Album":null,"Artist":null,"DisplayName":null,"Size":null,"Duration":null},{"UserEmail":"1234@qq.com","filepath":"/home/www/htdocs/upload/We_Are_The_Brave.mp3","MusicId":"2","Title":null,"Album":null,"Artist":null,"DisplayName":null,"Size":null,"Duration":null},{"UserEmail":"123@qq.com","filepath":"/upload/We_Are_The_Brave.mp3","MusicId":"3","Title":null,"Album":null,"Artist":null,"DisplayName":null,"Size":null,"Duration":null}]
     */

    public int error;
    public boolean success;
    public List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public int getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class ResultBean {
        /**
         * UserEmail : 123@qq.com
         * filepath : 1234
         * MusicId : 1
         * Title : null
         * Album : null
         * Artist : null
         * DisplayName : null
         * Size : null
         * Duration : null
         */

        public String UserEmail;
        public String filepath;
        public String MusicId;
        public String Title;
        public String Album;
        public String Artist;
        public String DisplayName;
        public String Size;
        public String Duration;

        public void setMusicId(String musicId) {
            MusicId = musicId;
        }

        public int getMusicId() {
            if(MusicId!=null){
                return Integer.parseInt(MusicId);
            }
            return 0;
        }

        public int getSize() {
            if(Size!=null){
                return Integer.parseInt(Size);
            }
            return 0;
        }

        public int getDuration() {
            if(Duration!=null){
                return Integer.parseInt(Duration);
            }
            return 0;
        }

        public String getFilepath() {
            return filepath;
        }

        public String getUserEmail() {
            return UserEmail;
        }

        public String getAlbum() {
            return Album;
        }

        public String getArtist() {
            return Artist;
        }

        public String getDisplayName() {
            return DisplayName;
        }

        public String getTitle() {
            return Title;
        }

    }
}
