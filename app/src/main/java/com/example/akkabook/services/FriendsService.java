package com.example.akkabook.services;

import com.example.akkabook.api.FriendsApi;
import com.example.akkabook.dataModel.FriendModel;
import com.example.akkabook.di.DaggerFriendsApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class FriendsService {

    private static final String BASE_URL = "domainName";

    @Inject
    public FriendsApi api;

    public static FriendsService instance;

    private FriendsService(){
        DaggerFriendsApiComponent.create().inject(this);
    }

    public static FriendsService getInstance(){
        if(instance == null){
            instance = new FriendsService();
        }
        return instance;
    }

    public Single<List<FriendModel>> getFriends(){
        return api.getFriends();
    }

}
