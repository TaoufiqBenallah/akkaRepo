package com.example.akkabook.services;

import com.example.akkabook.api.SuggestionsApi;
import com.example.akkabook.api.UserConectedApi;
import com.example.akkabook.dataModel.UserModel;
import com.example.akkabook.di.DaggerUserConnectedApiComponent;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserConnectedService {

    public static String BASE_URL="jfgj";
    private static UserConnectedService instance;

    @Inject
    public UserConectedApi api;

    public UserConnectedService(){
        DaggerUserConnectedApiComponent.create().inject(this);
    }

    public static UserConnectedService getInstance(){
        if(instance == null ){
            instance = new UserConnectedService();
        }
        return instance;
    }


    public Single<UserModel> getUserConnected(){
        return api.getUserConnected();
    }

}
