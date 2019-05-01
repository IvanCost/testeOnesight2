package com.example.testeonsigth2.data.usecase;

import com.example.testeonsigth2.data.model.Config;
import com.example.testeonsigth2.data.remote.ApiService;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetConfiguration {

    ApiService apiService;

    @Inject
    GetConfiguration(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Config> execute() {
        return apiService.getConfiguration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
