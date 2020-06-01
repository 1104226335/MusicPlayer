package com.GraduationDesign.MusicPlayer.ui.settings.feedback;

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
import com.GraduationDesign.MusicPlayer.data.jsonmodel.MyFeedbackBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
//
public class HandleFeedbackAdapter extends RecyclerView.Adapter<HandleFeedbackAdapter.FeedbackViewHolder>{
    private Context fContext;
    List<MyFeedbackBean.ResultBean> fFeedbackList;
    HandleFeedbackAdapter.OnAction action;
    public HandleFeedbackAdapter(Context context, List<MyFeedbackBean.ResultBean> feedbackList){
        this.fContext = context;
        this.fFeedbackList = feedbackList;
    }
    public interface OnAction{
        void onAction(View v, int feedbackId, int position);
    }

    @NonNull
    @Override
    public HandleFeedbackAdapter.FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HandleFeedbackAdapter.FeedbackViewHolder(LayoutInflater.from(fContext).inflate(R.layout.item_feedback_handle,null));
    }

    @Override
    public void onBindViewHolder(@NonNull HandleFeedbackAdapter.FeedbackViewHolder holder, final int position) {
        Glide.with(fContext)
                .load(fFeedbackList.get(position).getUserPic())
                .apply(RequestOptions.bitmapTransform(new CircleCrop())
                        .error(R.mipmap.bro1))
                .into(holder.userPic);
        holder.commentTime.setText(fFeedbackList.get(position).getFeedbackDate());
        holder.userName.setText(fFeedbackList.get(position).getUserName());
        holder.commentContent.setText(fFeedbackList.get(position).getContent());
        holder.action.setVisibility(View.VISIBLE);
        if (fFeedbackList.get(position).getFeed()==null){
            holder.hint.setVisibility(View.GONE);
            holder.feed.setVisibility(View.GONE);
        }else {
            holder.feed.setText(fFeedbackList.get(position).getFeed());
            holder.action.setVisibility(View.GONE);
        }
        final int feedbaclId = fFeedbackList.get(position).getFeedbackId();
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.onAction(v,feedbaclId,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(fFeedbackList == null)return 0;
        else {
            return fFeedbackList.size();
        }

    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder{
        ImageView userPic;
        TextView userName,commentTime,commentContent,feed,hint;
        FrameLayout action;

        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            userPic = itemView.findViewById(R.id.img_feedback_user_pic);
            userName = itemView.findViewById(R.id.tv_feedback_user_name);
            commentTime = itemView.findViewById(R.id.tv_feedback_time);
            commentContent = itemView.findViewById(R.id.tv_feedback_content);
            action = itemView.findViewById(R.id.feedback_layout_action);
            feed = itemView.findViewById(R.id.tv_feedback_feed);
            hint = itemView.findViewById(R.id.tv_feedback_hint);
        }
    }

    public void setAction(HandleFeedbackAdapter.OnAction action) {
        this.action = action;
    }
}
