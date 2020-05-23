package com.GraduationDesign.MusicPlayer.ui.local;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.GraduationDesign.MusicPlayer.RxBus;
import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.BodyBean;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.event.PlaySongEvent;
import com.GraduationDesign.MusicPlayer.utils.SecretUtil;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;
import com.bumptech.glide.Glide;
import com.to.aboomy.banner.HolderCreator;

import java.util.List;

public class ImageBannerHolder implements HolderCreator {
    Context context;
    String APIKey;
    List<String> recommends ;
    public ImageBannerHolder(List<String> myMusicId){
        this.recommends = myMusicId;
    }

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
        //内部实现点击事件
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchTop(recommends.get(index));
            }
        });
        return iv;
    }
    public void onSearchTop(String MusicId){
        CommonApi.getWyMusic(MusicId, APIKey, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                BodyBean wyMusic = (BodyBean)o;
                Song song = new Song();
                song.setId(wyMusic.getMid());
                song.setAlbum(wyMusic.getAlbum());
                song.setPath(wyMusic.getUrl());
                song.setTitle(wyMusic.getTitle());
                song.setArtist(wyMusic.getAuthor());
                song.setDisplayName(wyMusic.getTitle());
                song.setSize(0);
                song.setDuration(200000);
                RxBus.getInstance().post(new PlaySongEvent(song));
            }

            @Override
            public void onError(Exception e) {

            }
        });


    }
}
