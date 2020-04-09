package com.example.akkabook;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.akkabook.dataModel.FriendModel;
import com.example.akkabook.services.FriendsService;
import com.example.akkabook.viewModel.FriendsListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class FriendsListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Inject
    FriendsService friendsService;

    @InjectMocks
    FriendsListViewModel friendsListViewModel = new FriendsListViewModel();

    Single<List<FriendModel>> single;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFriendqSuccess(){

        ArrayList<FriendModel> friends = new ArrayList<FriendModel>();
        FriendModel friendModel = new FriendModel(1, "jj", "jjj", false);
        friends.add(friendModel);

        single = Single.just(friends);

        Mockito.when(FriendsService.getInstance().getFriends());

        friendsListViewModel.refresh();

        Assert.assertEquals(1, friendsListViewModel.friendsList.getValue().size());
        Assert.assertEquals(false, friendsListViewModel.isLoading.getValue());

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

}
