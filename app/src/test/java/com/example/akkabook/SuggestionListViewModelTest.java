package com.example.akkabook;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.akkabook.dataModel.SuggestionModel;
import com.example.akkabook.services.SuggestionService;
import com.example.akkabook.viewModel.SuggestionListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class SuggestionListViewModelTest {

    @Mock
    SuggestionService suggestionService;
    @InjectMocks
    SuggestionListViewModel suggestionListViewModel = new SuggestionListViewModel();
    @Rule
    InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    Single<List<SuggestionModel>> single;

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
        SuggestionModel suggestionModel = new SuggestionModel(1, "hj", "j");
        ArrayList<SuggestionModel> suggestions = new ArrayList<SuggestionModel>();
        suggestions.add(suggestionModel);

        single = Single.just(suggestions);

        Mockito.when(SuggestionService.getInstance().getSuggestions());

        suggestionListViewModel.refresh();

        Assert.assertEquals(1, suggestionListViewModel.suggestionsList.getValue().size());
        Assert.assertEquals(false, suggestionListViewModel.isLoading.getValue());
    }


}
