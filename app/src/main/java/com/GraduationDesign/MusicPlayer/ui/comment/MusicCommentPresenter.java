package com.GraduationDesign.MusicPlayer.ui.comment;

import android.util.Log;

import com.GraduationDesign.MusicPlayer.Web.CommonApi;
import com.GraduationDesign.MusicPlayer.Web.ErrorCode;
import com.GraduationDesign.MusicPlayer.Web.ResultCallback;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyCommentBean;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyComment;
import com.GraduationDesign.MusicPlayer.data.source.AppRepository;
import com.GraduationDesign.MusicPlayer.ui.details.PlayListDetailsContract;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;

import java.util.ArrayList;
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
                Log.e("MusicComment","调用");
                Log.e("MusicComment",Integer.toString(hotComments.size()));
                mView.setAdapter(hotComments);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        CommonApi.checkMyComment("get", MusicId, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                MyCommentBean myCommentBean = (MyCommentBean)o;
                Log.e("MusicComment","返回");
                List<MyCommentBean.ResultBean> resultBeans = myCommentBean.getResult();
                List<WyComment.HotCommentsBean> hotCommentsBeans = new ArrayList<>();
                WyComment.HotCommentsBean bean = new WyComment.HotCommentsBean();
                WyComment.UserBean user = new WyComment.UserBean();
                for(MyCommentBean.ResultBean tmp : resultBeans){
                    bean.setCommentId(Integer.toString(tmp.getCommentId()));
                    bean.setContent(tmp.getContent());
                    bean.setTime(TimeHelper.dateToLong(tmp.getCommentDate()));
                    bean.setUser(user);
                    bean.getUser().setNickname(tmp.getUserName());
                    bean.getUser().setAvatarUrl(tmp.getUserPic());
                    hotCommentsBeans.add(bean);
                }
                Log.e("MusicComment","结束");
                mView.setAdapter(hotCommentsBeans);

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
}
