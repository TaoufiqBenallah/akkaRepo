package com.example.akkabook.di;

import com.example.akkabook.services.SuggestionService;
import com.example.akkabook.viewModel.SuggestionListViewModel;

import dagger.Component;

@Component(modules = { ApiModule.class })
public interface SuggestionsApiComponent {
    void inject(SuggestionService service);
    void inject(SuggestionListViewModel suggestionListViewModel);
}