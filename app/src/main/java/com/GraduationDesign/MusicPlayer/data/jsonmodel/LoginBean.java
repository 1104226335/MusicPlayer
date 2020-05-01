package com.GraduationDesign.MusicPlayer.data.jsonmodel;

import java.util.List;

public class LoginBean {

    /**
     * error : 0
     * success : true
     * result : [{"UserName":"百庆","UserID":"v30270007T","UserEmail":"123@qq.com","Message":"success"}]
     */

    public int error;
    public boolean success;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * UserName : 百庆
         * UserID : v30270007T
         * UserEmail : 123@qq.com
         * Message : success
         */

        public String UserName;
        public String UserID;
        public String UserEmail;
        public int UserIdentity;
        public String Message;

        public int getUserIdentity() {
            return UserIdentity;
        }

        public String getMessage() {
            return Message;
        }

        public String getUserEmail() {
            return UserEmail;
        }

        public String getUserID() {
            return UserID;
        }

        public String getUserName() {
            return UserName;
        }
    }

    public int getError() {
        return error;
    }

    public List<ResultBean> getResult() {
        return result;
    }
    public boolean getSuccess() {
        return success;
    }

}
