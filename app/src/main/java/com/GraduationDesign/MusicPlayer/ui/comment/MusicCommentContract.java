package com.GraduationDesign.MusicPlayer.ui.comment;

import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyComment;
import com.GraduationDesign.MusicPlayer.data.model.PlayList;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.ui.base.BasePresenter;
import com.GraduationDesign.MusicPlayer.ui.base.BaseView;
import com.GraduationDesign.MusicPlayer.ui.details.PlayListDetailsContract;

import java.util.List;

public interface MusicCommentContract {
    interface View extends BaseView<MusicCommentContract.Presenter> {

        void showLoading();

        void hideLoading();

        void handleError(Throwable e);

        void setAdapter(List<WyComment.HotCommentsBean> hotComments);

    }

    interface Presenter  {
        void start();

        void sendComment(String content);
    }
}
