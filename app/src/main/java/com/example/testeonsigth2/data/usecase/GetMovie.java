package com.example.testeonsigth2.data.usecase;

import com.example.testeonsigth2.data.model.Filme;
import com.example.testeonsigth2.data.remote.ApiService;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetMovie {

    ApiService apiService;

    @Inject
    GetMovie(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Filme> execute(int movieId) {
        return apiService.getMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
