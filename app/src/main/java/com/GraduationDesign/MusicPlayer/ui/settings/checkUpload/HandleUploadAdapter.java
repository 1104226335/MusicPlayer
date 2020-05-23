package com.GraduationDesign.MusicPlayer.ui.settings.checkUpload;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.data.jsonmodel.UploadMusicBean;
import com.GraduationDesign.MusicPlayer.data.model.Song;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HandleUploadAdapter extends RecyclerView.Adapter<HandleUploadAdapter.ViewHolder>{
    private Context mContext;
    private List<UploadMusicBean.ResultBean> searchResults;
    HandleUploadAdapter.OnAction action;
    public HandleUploadAdapter(Context context,List<UploadMusicBean.ResultBean> searchResults){
        this.mContext = context;
        this.searchResults = searchResults;
    }
    public interface OnAction{
        void onAction(View v,int commentId,int position);
        void display(Song songs);
    }

    @NonNull
    @Override
    public HandleUploadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HandleUploadAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_play_list_net,null));
    }

    @Override
    public void onBindViewHolder(@NonNull HandleUploadAdapter.ViewHolder holder, final int position) {
        final Song song = new Song();
        song.setId(searchResults.get(position).getMusicId());
        song.setSize(0);
        song.setDuration(200000);
        song.setPath(searchResults.get(position).getFilepath());
        song.setTitle(searchResults.get(position).getTitle());
        song.setDisplayName(searchResults.get(position).getTitle());
        song.setArtist(searchResults.get(position).getArtist());
        holder.songAuthor.setText(searchResults.get(position).getArtist());
        holder.songTitle.setText(searchResults.get(position).getTitle());
        Glide.with(mContext)
                .load(R.mipmap.bro1)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.bro1)
                        .error(R.mipmap.bro2))
                .into(holder.songCover);
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.onAction(v,searchResults.get(position).getMusicId(),position);
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.display(song);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(searchResults != null){
            return searchResults.size();
        }else{
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView songCover;
        TextView songTitle,songAuthor;
        FrameLayout action;
        LinearLayout linearLayout;
        public ViewHolder(View view){
            super(view);
            songCover = view.findViewById(R.id.image_view_album_net);
            songTitle = view.findViewById(R.id.text_view_name_net);
            songAuthor = view.findViewById(R.id.text_view_info_net);
            action = view.findViewById(R.id.layout_action_net);
            linearLayout = view.findViewById(R.id.ll_recommend_song_play_net);
        }
    }
    public void setAction(HandleUploadAdapter.OnAction action) {
        this.action = action;
    }
}
