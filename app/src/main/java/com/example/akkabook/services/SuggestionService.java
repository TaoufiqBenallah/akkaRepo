package com.example.akkabook.services;

import com.example.akkabook.api.FriendsApi;
import com.example.akkabook.api.SuggestionsApi;
import com.example.akkabook.dataModel.SuggestionModel;
import com.example.akkabook.di.DaggerSuggestionsApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class SuggestionService {

    private static String BASE_URL = "Basic";

    private static SuggestionService instance;

    @Inject
    public SuggestionsApi api;

    private SuggestionService(){
        DaggerSuggestionsApiComponent.create().inject(this);
    }

    public static SuggestionService getInstance(){
        if(instance == null){
            instance = new SuggestionService();
        }
        return instance;
    }

    public Single<List<SuggestionModel>> getSuggestions(){
        return api.getSuggestions();
    }

}
