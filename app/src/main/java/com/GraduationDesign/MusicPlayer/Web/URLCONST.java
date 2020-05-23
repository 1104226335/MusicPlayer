package com.GraduationDesign.MusicPlayer.Web;

/**
 * Created by zhao on 2016/10/20.
 */

public class URLCONST {
        public static String nameSpace_system = "https://www.baiqing.work";
        public static boolean isRSA = false;
        public static String method_RegisterToService = nameSpace_system+"/php/kz_register.php";
        public static String method_loginToService = nameSpace_system+"/php/kz_login.php";
        public static String method_ChangeEmailToService = nameSpace_system+"/php/changeEmail.php";
        public static String method_ChangePassToService = nameSpace_system+"/php/changePassword.php";

        public static String method_sendmycomment = nameSpace_system+"/php/checkcomment.php";
        public static String method_checkmycomment = nameSpace_system+"/php/getComment.php";
        public static String method_sendmyfeedback = nameSpace_system+"/php/checkFeedback.php";
        public static String method_checkmyfeedback = nameSpace_system+"/php/getFeedback.php";

        public static String method_getCurAppVersion = nameSpace_system+"/apk/MusicUpdate.php";

        public static String method_upLoadMusic = nameSpace_system+"/php/GetMusicFromAndroid.php";
        public static String method_checkLoadMusic = nameSpace_system+"/php/checkUpdateMusic.php";

        public static String method_upLoadPic = nameSpace_system+"/php/GetPicFromAndroid.php";

        public static String method_getRecommend = nameSpace_system+"/php/GetRecommemd.php";
        public static String method_updateRecommend = nameSpace_system+"/php/MyRecommend.php";
        public static String method_sendHistory = nameSpace_system+"/php/GetHistory.php";
        public static String method_sendMusicList= nameSpace_system+"/php/GetMusicList.php";

        public static String method_getBanner = nameSpace_system+"/php/GetBanner.php";

        public static String Wy_List_Api = "https://api88.net/api/netease/";
        public static String Wy_comment_Api = "http://music.163.com/api/v1/resource/comments/R_SO_4_";

}

