package com.GraduationDesign.MusicPlayer.ui.search;

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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private Context mContext;
    private List<BodyBean> searchResults;
    private PlayList searchSongs = new PlayList();
    private SearchContract.ActionCallback actionCallback;
    public SearchAdapter(Context context,List<BodyBean> searchResults,SearchContract.ActionCallback actionCallback){
        this.mContext = context;
        this.searchResults = searchResults;
        this.actionCallback = actionCallback;
    }


    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchAdapter.SearchViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_play_list_net,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, final int position) {
         Song song = new Song();
        song.setId(searchResults.get(position).getId());
        song.setSize(0);
        song.setDuration(200000);
        song.setPath(searchResults.get(position).getUrl());
        song.setTitle(searchResults.get(position).getTitle());
        song.setDisplayName(searchResults.get(position).getTitle());
        song.setArtist(searchResults.get(position).getAuthor());
        searchSongs.addSong(song);
        holder.songAuthor.setText(searchResults.get(position).getAuthor());
        holder.songTitle.setText(searchResults.get(position).getTitle());
        Glide.with(mContext)
                .load(searchResults.get(position).getPic())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.bro1)
                        .error(R.mipmap.bro2))
                .into(holder.songCover);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionCallback.OnClickItem(searchSongs,position);
            }
        });
        holder.action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionCallback.onAction(v,searchSongs.getSongs().get(position));
            }
        });
    }

    public void clearSongList(){
        searchSongs.setSongs(new ArrayList<Song>());
    }

    @Override
    public int getItemCount() {
        if(searchResults != null){
            return searchResults.size();
        }else{
            return 0;
        }

    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView songCover;
        TextView songTitle,songAuthor;
        AppCompatImageView action;
        LinearLayout linearLayout;
        public SearchViewHolder(View view){
            super(view);
            songCover = view.findViewById(R.id.image_view_album_net);
            songTitle = view.findViewById(R.id.text_view_name_net);
            songAuthor = view.findViewById(R.id.text_view_info_net);
            action = view.findViewById(R.id.image_button_action_net);
            linearLayout = view.findViewById(R.id.ll_recommend_song_play_net);
        }
    }
}
