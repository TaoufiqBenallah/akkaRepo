package com.example.akkabook.di;

import com.example.akkabook.services.FriendsService;
import com.example.akkabook.viewModel.FriendsListViewModel;

import dagger.Component;

@Component(modules= { ApiModule.class})
public interface FriendsApiComponent {
    void inject(FriendsService service);
    void inject(FriendsListViewModel friendsListViewModel);
}
