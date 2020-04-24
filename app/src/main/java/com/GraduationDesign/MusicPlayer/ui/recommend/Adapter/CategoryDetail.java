package com.GraduationDesign.MusicPlayer.ui.recommend.Adapter;

import java.util.List;

public class CategoryDetail {
    List<String> imageUrl,listName,id;
    String categoryName;
    public CategoryDetail(){

    }
    public void setImageUrl(List<String> ImageUrl){
        this.imageUrl = ImageUrl;
    }

    public void setListName(List<String> listName) {
        this.listName = listName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public List<String> getListName() {
        return listName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<String> getId() {
        return id;
    }
}
