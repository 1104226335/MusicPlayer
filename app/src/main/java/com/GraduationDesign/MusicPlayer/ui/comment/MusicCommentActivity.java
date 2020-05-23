package com.GraduationDesign.MusicPlayer.ui.comment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.Web.ErrorCode;
import com.GraduationDesign.MusicPlayer.Web.TextHelper;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyComment;
import com.GraduationDesign.MusicPlayer.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicCommentActivity extends BaseActivity implements MusicCommentContract.View{

    @BindView(R.id.rv_music_comment)
    RecyclerView musicComment;
    @BindView(R.id.toolbar_music_comment)
    Toolbar toolbar;
    @BindView(R.id.comment_input_text)
    TextView myCommentContent;
    @BindView(R.id.comment_input_send)
    Button contentSend;

    MusicCommentAdapter adapter ;
    MusicCommentContract.Presenter presenter;
    List<WyComment.HotCommentsBean> hotComments = new ArrayList<>();
    private String MusicId;

    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
                case ErrorCode.sendComment_success:
                    TextHelper.showLongText("评论成功,正在审核");
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MusicId = getIntent().getStringExtra("MusicId");
        setContentView(R.layout.activity_music_comment);
        ButterKnife.bind(this);
        supportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("评论");
        }
        musicComment.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MusicCommentAdapter(this,hotComments);
        musicComment.setAdapter(adapter);
        hotComments.clear();
        new MusicCommentPresenter(this,MusicId).start();
    }

    @OnClick(R.id.comment_input_send)
    public void sendContent(){
        String content = myCommentContent.getText().toString();
        if(!content.isEmpty()){
            presenter.sendComment(content,MusicId);
            myCommentContent.setText("");
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getUserEmail() {
        SharedPreferences shared = getSharedPreferences("LoginMsg",Context.MODE_PRIVATE);

        return shared.getString("UserEmail","error");
    }

    @Override
    public void getSendState(int code) {
        mhandler.sendMessage(mhandler.obtainMessage(code));
    }

    @Override
    public void handleError(Throwable e) {

    }
    @Override
    public void setAdapter(List<WyComment.HotCommentsBean> presenterList){
        Log.e("MusicComment","回调");
        Log.e("MusicComment",Integer.toString(presenterList.size()));
        hotComments.addAll(presenterList);
        mhandler.sendMessage(mhandler.obtainMessage(1));
    }


    @Override
    public void setPresenter(MusicCommentContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
