package com.GraduationDesign.MusicPlayer.ui.settings.checkcomment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyCommentBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CheckCommentAdapter extends RecyclerView.Adapter<CheckCommentAdapter.CommentViewHolder> {

    private Context mContext;
    List<MyCommentBean.ResultBean> mCommentList;
    OnAction action;
    public CheckCommentAdapter(Context context, List<MyCommentBean.ResultBean> commentList){
        this.mContext = context;
        this.mCommentList = commentList;
    }
    public interface OnAction{
        void onAction(View v,int commentId,int position);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CommentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_music_comment,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(mCommentList.get(position).getUserPic())
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                .error(R.mipmap.bro1))
                .into(holder.userPic);
        holder.commentTime.setText(mCommentList.get(position).getCommentDate());
        holder.userName.setText(mCommentList.get(position).getUserName());
        holder.commentContent.setText(mCommentList.get(position).getContent());
        holder.action.setVisibility(View.VISIBLE);
        final int commentId = mCommentList.get(position).getCommentId();
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.onAction(v,commentId,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mCommentList == null)return 0;
        else {
            return mCommentList.size();
        }

    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        ImageView userPic;
        TextView userName,commentTime,commentContent;
        FrameLayout action;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userPic = itemView.findViewById(R.id.img_comment_user_pic);
            userName = itemView.findViewById(R.id.tv_comment_user_name);
            commentTime = itemView.findViewById(R.id.tv_comment_time);
            commentContent = itemView.findViewById(R.id.tv_comment_content);
            action = itemView.findViewById(R.id.comment_layout_action);
        }
    }

    public void setAction(OnAction action) {
        this.action = action;
    }
}
