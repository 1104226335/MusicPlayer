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
        recommends.add(new WyRecommend("http://p1.music.126.net/CbzZ6Iv5Rq0uwf-LBxboog==/109951164961326571.jpg?imageView","五月继续加油，愿所有的结果都不辜负努力","4939476056"));
        recommends.add(new WyRecommend("http://p1.music.126.net/d14C5k5RMmPdxBTJGxgA7A==/109951164962179056.jpg?imageView","[周末假期运势] 宅在家 宜听歌吸猫 种花晒太阳","3091142760"));
        recommends.add(new WyRecommend("http://p1.music.126.net/bSpZApdtYshI4VKUnp81HA==/109951164961414245.jpg?imageView","极光轻音乐 | 听了让你落泪的轻音乐","4906766923"));
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
