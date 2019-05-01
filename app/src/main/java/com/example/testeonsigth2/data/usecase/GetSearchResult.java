package com.example.testeonsigth2.data.usecase;

import com.example.testeonsigth2.data.model.Filmes;
import com.example.testeonsigth2.data.remote.ApiService;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetSearchResult {

    ApiService apiService;

    @Inject
    GetSearchResult(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Filmes> execute(String query) {
        return apiService.getSearchResult(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
