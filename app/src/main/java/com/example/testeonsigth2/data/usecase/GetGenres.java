package com.example.testeonsigth2.data.usecase;

import com.example.testeonsigth2.data.model.Generos;
import com.example.testeonsigth2.data.remote.ApiService;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetGenres {

    ApiService apiService;

    @Inject
    GetGenres(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Generos> execute() {
        return apiService.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
