package com.example.testeonsigth2.data.usecase;

import com.example.testeonsigth2.data.model.Filmes;
import com.example.testeonsigth2.data.remote.ApiService;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetMovies {

    ApiService apiService;

    @Inject
    GetMovies(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Filmes> execute(String releaseDate, int page) {
        return apiService.getMovies(releaseDate, "primary_release_date.asc", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
