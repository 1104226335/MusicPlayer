package com.GraduationDesign.MusicPlayer.ui.recommend;

import com.GraduationDesign.MusicPlayer.data.model.PlayList;
import com.GraduationDesign.MusicPlayer.data.model.Song;

import java.util.List;

public interface RecommendItemLitener {
//    public void OnClickItem(Song song);
    public void OnClickItem(PlayList songs, int position);
}
