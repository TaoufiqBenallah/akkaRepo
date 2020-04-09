package com.example.akkabook.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.akkabook.dataModel.FriendModel;
import com.example.akkabook.di.DaggerFriendsApiComponent;
import com.example.akkabook.services.FriendsService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FriendsListViewModel extends ViewModel {
    public MutableLiveData<List<FriendModel>> friendsList = new MutableLiveData<List<FriendModel>>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    @Inject
    public FriendsService friendsService;

    public FriendsListViewModel(){
        super();
        DaggerFriendsApiComponent.create().inject(this);
    }
    CompositeDisposable disposable = new CompositeDisposable();

    public void refresh(){
        fetchFriendsList();
    }

    public void fetchFriendsList() {

        isLoading.setValue(true);

        disposable.add(
                friendsService.getFriends()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<FriendModel>>() {

                            @Override
                            public void onSuccess(List<FriendModel> friendsModels) {
                                isLoading.setValue(false);
                                friendsList.setValue(friendsModels);
                            }

                            @Override
                            public void onError(Throwable e) {
                                isLoading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );;
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        disposable.clear();
    }

}
