package com.GraduationDesign.MusicPlayer.ui.recommend.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import com.GraduationDesign.MusicPlayer.R;

public class CategoryMusicAdapter extends RecyclerView.Adapter<CategoryMusicAdapter.MusicViewHolder> {
    private List<String> imgUrl;
    private List<String> musicListName;
    private List<String> musicListId;
    private Context mContext;
    CategoryMusicListener itemClick;
    public interface CategoryMusicListener {
        void clickItem(String listid,String name);
    }

    public void setOnCategoryMusicListener(CategoryMusicListener listener) {
        itemClick = listener;
    }

    public CategoryMusicAdapter(Context context, List<String> imgUrl, List<String> musicListName,List<String> musicListId){
        this.imgUrl = imgUrl;
        this.musicListName = musicListName;
        this.mContext = context;
        this.musicListId = musicListId;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MusicViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_category_music,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder viewHolder, int i) {
        viewHolder.musicListName.setText(musicListName.get(i));
        Glide.with(mContext)
                .load(imgUrl.get(i))
                .apply(new RequestOptions()
                .placeholder(R.mipmap.bro1)
                .error(R.mipmap.bro2))
                .into(viewHolder.musicListCover);
        final String listId = musicListId.get(i);
        final String listname = musicListName.get(i);
        viewHolder.musicListCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.clickItem(listId,listname);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgUrl.size();
    }

    class MusicViewHolder extends RecyclerView.ViewHolder{
        ImageView musicListCover;
        TextView musicListName;
        public MusicViewHolder(View view){
            super(view);
            musicListCover = view.findViewById(R.id.iv_item_category_novel_cover);
            musicListName = view.findViewById(R.id.tv_item_category_novel_name);
        }
    }
}
