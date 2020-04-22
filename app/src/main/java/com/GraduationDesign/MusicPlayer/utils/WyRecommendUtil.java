package com.GraduationDesign.MusicPlayer.utils;

import android.util.Log;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyRecommend;
import com.GraduationDesign.MusicPlayer.ui.local.Adapter.CategoryDetail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WyRecommendUtil {

    public static List<CategoryDetail> getRecommendFromWy(){
        List<CategoryDetail> recommends = new ArrayList<>();
        CategoryDetail categoryDetail = new CategoryDetail();
        List<String> musicListCover = new ArrayList<>();
        List<String> musicListName = new ArrayList<>();
        List<String> musicListId = new ArrayList<>();
        Document doc;
        Log.e("MusicWy","this");
        try {
            doc =Jsoup.connect("https://www.baiqing.work/API/WyMusicRecommend.html").get();
//            Log.e("MusicWy",doc.toString());
            Element divs = doc.getElementsByClass("m-cvrlst f-cb").get(0);
            for(Element li : divs.children()){

                Element item = li.getElementsByClass("u-cover u-cover-1").get(0);
                musicListCover.add(item.child(0).attr("src"));
                musicListId.add(item.child(1).attr("data-res-id"));
                musicListName.add(item.child(1).attr("title"));

                Log.e("MusicWy",item.child(0).attr("src"));
            }
            categoryDetail.setId(musicListId);
            categoryDetail.setImageUrl(musicListCover);
            categoryDetail.setListName(musicListName);
            categoryDetail.setCategoryName("网易云推荐");
            recommends.add(categoryDetail);
        }catch (IOException e){e.printStackTrace();}
        return recommends;
    }
}
