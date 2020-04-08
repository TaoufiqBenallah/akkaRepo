package com.example.akkabook.dataModel;

import com.google.gson.annotations.SerializedName;

public class SuggestionModel {
    @SerializedName("id")
    private int id;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("username")
    private String username;

    public SuggestionModel(int id, String imageUrl, String username) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
