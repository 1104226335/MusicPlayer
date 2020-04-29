package com.GraduationDesign.MusicPlayer.ui.search;

import android.view.View;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.BodyBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyComment;
import com.GraduationDesign.MusicPlayer.data.model.PlayList;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.ui.base.BaseView;
import com.GraduationDesign.MusicPlayer.ui.comment.MusicCommentContract;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView<SearchContract.Presenter> {

        void showLoading();

        void hideLoading();

        void handleError(Throwable e);

        void setAdapter(List<BodyBean> searchResult);
    }

    interface Presenter  {
        void addSongToPlayList(Song song, PlayList playList);
        void start();
        void stop();
        void search(String key);
    }
    interface ActionCallback {
        void onAction(android.view.View actionView, Song song);
        public void OnClickItem(PlayList songs, int position);
    }
}
