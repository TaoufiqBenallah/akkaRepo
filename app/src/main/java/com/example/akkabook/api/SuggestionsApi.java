package com.example.akkabook.api;

import com.example.akkabook.dataModel.SuggestionModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface SuggestionsApi {

    @GET("URLMF")
    public Single<List<SuggestionModel>> getSuggestions();
}
