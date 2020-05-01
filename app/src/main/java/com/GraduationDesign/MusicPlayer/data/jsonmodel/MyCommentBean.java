package com.GraduationDesign.MusicPlayer.data.jsonmodel;

import java.util.List;

public class MyCommentBean {

    /**
     * error : 0
     * success : true
     * result : [{"UserName":"百庆","commentDate":"2020-04-04 12:54:23","content":"content","musicId":"123456"}]
     */

    public int error;
    public boolean success;
    public List<ResultBean> result;

    public int getError() {
        return error;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class ResultBean {
        /**
         * UserName : 百庆
         * commentDate : 2020-04-04 12:54:23
         * content : content
         * musicId : 123456
         */

        public String UserName;
        public String commentDate;
        public String content;
        public String musicId;
        public String commentId;

        public String getUserName() {
            return UserName;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public String getContent() {
            return content;
        }

        public String getMusicId() {
            return musicId;
        }

        public int getCommentId() {
            if(commentId!=null){
                return Integer.parseInt(commentId);
            }
            return 0;
        }
    }
}
