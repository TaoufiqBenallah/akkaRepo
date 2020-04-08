package com.example.akkabook.api;

import com.example.akkabook.dataModel.UserModel;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UserConectedApi {
    @GET("USER")
    Single<UserModel> getUserConnected();

}
