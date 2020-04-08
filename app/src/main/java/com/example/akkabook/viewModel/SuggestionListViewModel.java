package com.example.akkabook.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.akkabook.dataModel.SuggestionModel;
import com.example.akkabook.services.SuggestionService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SuggestionListViewModel extends ViewModel {

    public MutableLiveData<List<SuggestionModel>> suggestionsList = new MutableLiveData<List<SuggestionModel>>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    @Inject
    public SuggestionService suggestionService;

    CompositeDisposable disposable = new CompositeDisposable();

    public SuggestionListViewModel(){
        super();
    }

    public void refresh(){
        fetchSuggestionsList();
    }

    public void fetchSuggestionsList() {

        isLoading.setValue(true);

        disposable.add(
                suggestionService.getSuggestions()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<SuggestionModel>>() {

                            @Override
                            public void onSuccess(List<SuggestionModel> suggestionsModels) {
                                isLoading.setValue(false);
                                suggestionsList.setValue(suggestionsModels);
                            }

                            @Override
                            public void onError(Throwable e) {
                                isLoading.setValue(false);
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
