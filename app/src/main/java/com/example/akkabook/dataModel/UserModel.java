package com.example.akkabook.dataModel;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id")
    private int id;

    @SerializedName("imageUrl")
    private String imageUrl;

    public UserModel(int id, String name){
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public int getId(){
        return this.id;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
}
