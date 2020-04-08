package com.example.akkabook.dataModel;

import com.google.gson.annotations.SerializedName;

public class FriendModel {
    @SerializedName("id")
    private int id;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("username")
    private String username;

    @SerializedName("isClose")
    private boolean isClose;

    public FriendModel(int id, String imageUrl, String username, boolean isClose) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.username = username;
        this.isClose = isClose;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public boolean isClose() {
        return isClose;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setClose(boolean close) {
        isClose = close;
    }
}
