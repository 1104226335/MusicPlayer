package com.GraduationDesign.MusicPlayer.ui.comment;

import android.util.Log;

import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ErrorCode;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyComment;
import com.GraduationDesign.MusicPlayer.data.source.AppRepository;
import com.GraduationDesign.MusicPlayer.ui.details.PlayListDetailsContract;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;

import java.util.List;


public class MusicCommentPresenter implements MusicCommentContract.Presenter{
    
    private MusicCommentContract.View mView;
    List<WyComment.HotCommentsBean> hotComments;
    String MusicId;
    public MusicCommentPresenter(MusicCommentContract.View view,String MusicId) {
        this.mView = view;
        this.MusicId = MusicId;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        Log.e("MusicComment",MusicId);

        CommonApi.getWyComment(MusicId, 10, 0, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                Log.e("MusicComment","finish");
                WyComment wyComment = (WyComment)o;
                hotComments = wyComment.getHotComments();
                hotComments.add(0,newcomment());
                Log.e("MusicComment","调用");
                Log.e("MusicComment",Integer.toString(hotComments.size()));
                mView.setAdapter(hotComments);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
    public void sendComment(String content,String MusicId){
        String email = mView.getUserEmail();
        //TODO send comment
        if(!email.equals("error")) {
            CommonApi.sendMyComment(email, MusicId, content, new ResultCallback() {
                @Override
                public void onFinish(Object o, int code) {
                    mView.getSendState(code);
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }
    }
    public WyComment.HotCommentsBean newcomment(){
        WyComment.HotCommentsBean hotCommentsBean = new WyComment.HotCommentsBean();
        hotCommentsBean.setContent("好听");
        hotCommentsBean.setTime(TimeHelper.getCurrentMillis());
        hotCommentsBean.setUser(new WyComment.UserBean());
        hotCommentsBean.getUser().setNickname("用户一号");
        hotCommentsBean.getUser().setAvatarUrl("https://c-ssl.duitang.com/uploads/item/201511/21/20151121171107_zMZcy.thumb.1000_0.jpeg");
        return hotCommentsBean;
    }
}
