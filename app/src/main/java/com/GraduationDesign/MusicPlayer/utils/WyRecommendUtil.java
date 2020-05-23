package com.GraduationDesign.MusicPlayer.utils;

import android.util.Log;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.BannerBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyMusicList;
import com.GraduationDesign.MusicPlayer.ui.recommend.Adapter.CategoryDetail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WyRecommendUtil {

    public static CategoryDetail getRecommendFromWy(){
        CategoryDetail categoryDetail = new CategoryDetail();
        List<MyMusicList> myMusicLists = new ArrayList<>();
        Document doc;
        Log.e("MusicWy","this");
        try {
            doc =Jsoup.connect("https://www.baiqing.work/API/WyMusicRecommend.html").get();
//            Log.e("MusicWy",doc.toString());
            Element divs = doc.getElementsByClass("m-cvrlst f-cb").get(0);
            for(Element li : divs.children()){

                Element item = li.getElementsByClass("u-cover u-cover-1").get(0);

                myMusicLists.add(new MyMusicList(item.child(0).attr("src"),
                        item.child(1).attr("title"),
                        item.child(1).attr("data-res-id")));

                Log.e("MusicWy",item.child(0).attr("src"));
            }
            categoryDetail.setMusicLists(myMusicLists);
            categoryDetail.setCategoryName("必听歌单");
        }catch (IOException e){e.printStackTrace();}
        return categoryDetail;
    }
    /**
     * 获取重定向地址
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String getRedirectUrl(String path) {
        String url = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
            conn.setInstanceFollowRedirects(false);
            conn.setConnectTimeout(5000);
            url = conn.getHeaderField("Location");
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
}
