package com.GraduationDesign.MusicPlayer.ui.comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyComment;
import com.GraduationDesign.MusicPlayer.utils.TimeHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Date;

public class MusicCommentAdapter extends RecyclerView.Adapter<MusicCommentAdapter.CommentViewHolder> {

    private Context mContext;
    List<WyComment.HotCommentsBean> mCommentList;
    public MusicCommentAdapter(Context context, List<WyComment.HotCommentsBean> commentList){
        this.mContext = context;
        this.mCommentList = commentList;
    }


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CommentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_music_comment,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mCommentList.get(position).getUser().getAvatarUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                .error(R.mipmap.bro1))
                .into(holder.userPic);
        holder.commentTime.setText(TimeHelper.stampToDate(mCommentList.get(position).getTime()));
        holder.userName.setText(mCommentList.get(position).getUser().getNickname());
        holder.commentContent.setText(mCommentList.get(position).getContent());
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


        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userPic = itemView.findViewById(R.id.img_comment_user_pic);
            userName = itemView.findViewById(R.id.tv_comment_user_name);
            commentTime = itemView.findViewById(R.id.tv_comment_time);
            commentContent = itemView.findViewById(R.id.tv_comment_content);
        }
    }
}
