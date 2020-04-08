package com.example.akkabook.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.akkabook.dataModel.UserModel;
import com.example.akkabook.services.UserConnectedService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MenuList extends ViewModel {

    public MutableLiveData<UserModel> userConnected = new MutableLiveData<UserModel>();
    MutableLiveData<Boolean> isOptionsDisplayed = new MutableLiveData<Boolean>();

    @Inject
    public UserConnectedService userConnectedService;

    CompositeDisposable disposable = new CompositeDisposable();



    public MenuList(){
        super();
    }

    public void getUserConnected(){
        fetchConnectedUser();
    }


    public void fetchConnectedUser(){

        isOptionsDisplayed.setValue(true);

        disposable.add(
            userConnectedService.getUserConnected()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<UserModel>() {

                        @Override
                        public void onSuccess(UserModel userModel) {
                            isOptionsDisplayed.setValue(false);
                            userConnected.setValue(userModel);
                        }

                        @Override
                        public void onError(Throwable e) {
                            isOptionsDisplayed.setValue(false);
                            e.printStackTrace();
                        }
                    })
        );
    }
    @Override
    protected void onCleared(){
        super.onCleared();
        disposable.clear();
    }

}
