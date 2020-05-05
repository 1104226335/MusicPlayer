package com.GraduationDesign.MusicPlayer.ui.recommend.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.BodyBean;
import com.GraduationDesign.MusicPlayer.data.model.PlayList;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.GraduationDesign.MusicPlayer.ui.recommend.RecommendItemLitener;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.WyRecommendListBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecommendListAdapter extends RecyclerView.Adapter<RecommendListAdapter.RecommendViewHolder> {


    private Context contexts;
    private List<BodyBean> categoryDetailss;
    private RecommendItemLitener musicListListener;
    private ActionCallback mCallback;
    private PlayList recommentList = new PlayList();
    public RecommendListAdapter(Context context, List<BodyBean> categoryDetails,final RecommendItemLitener musicListListener){
        this.contexts = context;
        this.categoryDetailss = categoryDetails;
        this.musicListListener = musicListListener;
    }
    @Override
    public RecommendListAdapter.RecommendViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        return new RecommendListAdapter.RecommendViewHolder(LayoutInflater.from(contexts).inflate(R.layout.item_play_list_net,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendListAdapter.RecommendViewHolder holder, final int positio) {
        final Song song = new Song();
        song.setId(categoryDetailss.get(positio).getId());
        song.setSize(0);
        song.setDuration(200000);
        song.setPath(categoryDetailss.get(positio).getUrl());
        song.setTitle(categoryDetailss.get(positio).getTitle());
        song.setDisplayName(categoryDetailss.get(positio).getTitle());
        song.setArtist(categoryDetailss.get(positio).getAuthor());
        song.setAlbum(categoryDetailss.get(positio).getPic());
        recommentList.addSong(song);
        holder.songAuthor.setText(categoryDetailss.get(positio).getAuthor());
        holder.songTitle.setText(categoryDetailss.get(positio).getTitle());
        Glide.with(contexts)
                .load(categoryDetailss.get(positio).getPic())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.bro1)
                        .error(R.mipmap.bro2))
                .into(holder.songCover);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicListListener.OnClickItem(recommentList,positio);
            }
        });
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onAction(v, song);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryDetailss.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{
        ImageView songCover;
        TextView songTitle,songAuthor;
        AppCompatImageView action;
        LinearLayout linearLayout;
        public RecommendViewHolder(View view){
            super(view);
            songCover = view.findViewById(R.id.image_view_album_net);
            songTitle = view.findViewById(R.id.text_view_name_net);
            songAuthor = view.findViewById(R.id.text_view_info_net);
            action = view.findViewById(R.id.image_button_action_net);
            linearLayout = view.findViewById(R.id.ll_recommend_song_play_net);
        }

    }
    public void setActionCallback(ActionCallback callback) {
        mCallback = callback;
    }
    public interface ActionCallback {
        void onAction(View actionView, Song song);
    }
}
