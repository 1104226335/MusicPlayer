package com.GraduationDesign.MusicPlayer.ui.recommend.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.GraduationDesign.MusicPlayer.R;
import com.GraduationDesign.MusicPlayer.ui.recommend.MusicListListener;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private Context contexts;
    private List<CategoryDetail> categoryDetailss;
    private MusicListListener musicListListener;
    public CategoryAdapter(Context context, List<CategoryDetail> categoryDetails,final MusicListListener musicListListener){
        this.contexts = context;
        this.categoryDetailss = categoryDetails;
        this.musicListListener = musicListListener;
    }
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        return new CategoryViewHolder(LayoutInflater.from(contexts).inflate(R.layout.item_category,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int positio) {
        holder.TVcategoryName.setText(categoryDetailss.get(positio).getCategoryName());
        holder.TVcategoryDetail.setLayoutManager(new GridLayoutManager(contexts,2));
        CategoryMusicAdapter categoryMusicAdapter = new CategoryMusicAdapter(contexts
                ,categoryDetailss.get(positio).getImageUrl()
                ,categoryDetailss.get(positio).getListName()
                ,categoryDetailss.get(positio).getId());
        categoryMusicAdapter.setOnCategoryMusicListener(new CategoryMusicAdapter.CategoryMusicListener() {
            @Override
            public void clickItem(String listid,String  name,String ListPic) {
                musicListListener.OnClickItem(listid,name,ListPic);
            }
        });
        holder.TVcategoryDetail.setAdapter(categoryMusicAdapter);

    }

    @Override
    public int getItemCount() {
        return categoryDetailss.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView TVcategoryName;
        RecyclerView TVcategoryDetail;
        public CategoryViewHolder(View view){
            super(view);
            TVcategoryName = view.findViewById(R.id.tv_item_category_category_name);
            TVcategoryDetail = view.findViewById(R.id.rv_item_category_novel_list);

        }

    }

}
