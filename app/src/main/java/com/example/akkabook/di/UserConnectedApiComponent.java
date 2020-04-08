package com.example.akkabook.di;

import com.example.akkabook.services.UserConnectedService;
import com.example.akkabook.viewModel.MenuList;

import dagger.Component;

@Component(modules = { ApiModule.class})
public interface UserConnectedApiComponent {
    void inject(UserConnectedService service);
    void inject(MenuList menuList);
}