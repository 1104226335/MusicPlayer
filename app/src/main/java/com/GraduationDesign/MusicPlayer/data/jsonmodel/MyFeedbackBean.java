package com.GraduationDesign.MusicPlayer.data.jsonmodel;

import java.util.List;

public class MyFeedbackBean {
    public int error;
    public boolean success;
    public List<MyFeedbackBean.FResultBean> result;

    public int getError() {
        return error;
    }

    public List<MyFeedbackBean.FResultBean> getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class FResultBean {
        /**
         * UserName : 康铮
         * feedbackDate : 2020-05-05 12:54:23
         * content : content
         * musicId : 123456
         */

        public String UserName;
        public String feedbackDate;
        public String content;
        //public String musicId;
        public String feedbackId;

        public String getUserName() {
            return UserName;
        }

        public String getfeedbackDate() {
            return feedbackDate;
        }

        public String getContent() {
            return content;
        }

//        public String getMusicId() {
//            return musicId;
//        }

        public int getfeedbackId() {
            if(feedbackId!=null){
                return Integer.parseInt(feedbackId);
            }
            return 0;
        }
    }
}
