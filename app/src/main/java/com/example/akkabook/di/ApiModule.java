package com.example.akkabook.di;

import com.example.akkabook.api.FriendsApi;
import com.example.akkabook.api.SuggestionsApi;
import com.example.akkabook.api.UserConectedApi;
import com.example.akkabook.services.FriendsService;
import com.example.akkabook.services.SuggestionService;
import com.example.akkabook.services.UserConnectedService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public static String BASE_URL = "r";

    @Provides
    public FriendsApi provideFriendsApi(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(FriendsApi.class);
    }


    @Provides
    public SuggestionsApi provideSuggestionsApi(){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SuggestionsApi.class);
    }

    @Provides
    public UserConectedApi provideUserConnectedApi (){
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(UserConectedApi.class);
    }


    @Provides
    public FriendsService provideFriendsService (){
        return FriendsService.getInstance();
    }


    @Provides
    public SuggestionService provideSuggestionService (){
        return SuggestionService.getInstance();
    }

    @Provides
    public UserConnectedService provideUserConnectedService (){
        return UserConnectedService.getInstance();
    }


}
