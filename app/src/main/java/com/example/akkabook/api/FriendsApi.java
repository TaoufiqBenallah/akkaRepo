package com.example.akkabook.api;

import com.example.akkabook.dataModel.FriendModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface FriendsApi {
    @GET("UrlToGetFriends")
    Single<List<FriendModel>> getFriends();
}
