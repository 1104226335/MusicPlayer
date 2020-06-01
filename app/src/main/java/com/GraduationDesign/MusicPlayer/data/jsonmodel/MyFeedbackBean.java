package com.GraduationDesign.MusicPlayer.data.jsonmodel;

import java.util.List;

public class MyFeedbackBean {

    /**
     * error : 0
     * success : true
     * result : [{"UserName":"百庆","feedbackDate":"2020-02-02 02:02:02","content":"1231313131313","feedbackId":"1"}]
     */

    private int error;
    private boolean success;
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public int getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResultBean {
        /**
         * UserName : 百庆
         * feedbackDate : 2020-02-02 02:02:02
         * content : 1231313131313
         * feedbackId : 1
         */

        private String UserName;
        private String  UserPic;
        private String feedbackDate;
        private String content;
        private String feedbackId;
        public String feed;

        public String getUserPic() {
            return UserPic;
        }

        public void setUserPic(String userPic) {
            UserPic = userPic;
        }

        public String getFeed() {
            return feed;
        }

        public void setFeed(String feed) {
            this.feed = feed;
        }

        public String getContent() {
            return content;
        }

        public String getUserName() {
            return UserName;
        }

        public String getFeedbackDate() {
            return feedbackDate;
        }

        public int getFeedbackId() {
            if(feedbackId!=null){
                return Integer.parseInt(feedbackId);
            }
            return 0;
        }

        public void setFeedbackDate(String feedbackDate) {
            this.feedbackDate = feedbackDate;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setFeedbackId(String feedbackId) {
            this.feedbackId = feedbackId;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }
    }
}
