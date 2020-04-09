package com.example.akkabook;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.akkabook.dataModel.UserModel;
import com.example.akkabook.services.UserConnectedService;
import com.example.akkabook.viewModel.MenuList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class MenuListTest {


    @Mock
    UserConnectedService userConnectedService;
    @InjectMocks
    MenuList menuList = new MenuList();
    @Rule
    InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    Single<UserModel> single;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Before
    public void setUpRxSchedulers(){
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        command.run();
                    }
                }, false);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Test
    public void getSuggestionsSuccess(){
        UserModel userModel = new UserModel(1, "hj");

        single = Single.just(userModel);

        Mockito.when(UserConnectedService.getInstance().getUserConnected());

        menuList.getUserConnected();

        Assert.assertEquals(1, menuList.userConnected.getValue().getId());
        Assert.assertEquals("hj", menuList.userConnected.getValue().getImageUrl());
        Assert.assertEquals(false, menuList.isOptionsDisplayed.getValue());
    }


}
