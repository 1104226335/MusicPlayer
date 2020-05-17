package com.GraduationDesign.MusicPlayer.ui.local;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyRecommend;
import com.GraduationDesign.MusicPlayer.ui.recommend.RecommendListsActivity;
import com.GraduationDesign.MusicPlayer.utils.SecretUtil;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;
import com.bumptech.glide.Glide;
import com.to.aboomy.banner.HolderCreator;

import java.util.ArrayList;
import java.util.List;

public class ImageBannerHolder implements HolderCreator {
    Context context;
    String APIKey;
    List<WyRecommend> recommends = new ArrayList<>();
    @Override
    public View createView(final Context context, final int index, Object o) {
        this.context = context;
        try{
            APIKey = SecretUtil.md5(SecretUtil.md5("523077333")+
                    SecretUtil.sha1(TimeHelper.getStringDateForKey("yyyyMMddHH")));
        }catch (Exception e){
            e.printStackTrace();}

        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(iv).load(o).into(iv);
        recommends.add(new WyRecommend("https://p1.music.126.net/hPZNJQ9yekQZ5jLLmaX7bA==/109951164957259611.jpg","五月继续加油，愿所有的结果都不辜负努力","4939476056"));
        recommends.add(new WyRecommend("https://p2.music.126.net/9EjOKf8oNlgV5Cg43m73kQ==/109951164951601918.jpg","[周末假期运势] 宅在家 宜听歌吸猫 种花晒太阳","3091142760"));
        recommends.add(new WyRecommend("http://p2.music.126.net/LJsMADNvkK_0ilmXbKg_TQ==/109951164809294621.jpg","极光轻音乐 | 听了让你落泪的轻音乐","4906766923"));
        //内部实现点击事件
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchTop(recommends.get(index));

            }
        });
        return iv;
    }
    public void onSearchTop(WyRecommend wyRecommend){
        Intent intent = new Intent(((Activity)context),RecommendListsActivity.class);
        intent.putExtra("ListId",wyRecommend.getId());
        intent.putExtra("ListName",wyRecommend.getName());
        intent.putExtra("ListPic",wyRecommend.getCoverUrl());
        intent.putExtra("Key",APIKey);
        ((Activity)context).startActivity(intent);
    }
}
