package com.example.testeonsigth2.injection.module;

import com.example.testeonsigth2.data.usecase.GetConfiguration;
import com.example.testeonsigth2.data.usecase.GetGenres;
import com.example.testeonsigth2.data.usecase.GetMovie;
import com.example.testeonsigth2.data.usecase.GetMovies;
import com.example.testeonsigth2.data.usecase.GetSearchResult;
import com.example.testeonsigth2.presentation.detail.DetailContract;
import com.example.testeonsigth2.presentation.detail.DetailPresenter;
import com.example.testeonsigth2.presentation.main.MainContract;
import com.example.testeonsigth2.presentation.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    DetailContract.Presenter providesDetailPresenter(GetConfiguration getConfiguration, GetMovie getMovie) {
        return new DetailPresenter(getConfiguration, getMovie);
    }

    @Provides
    MainContract.Presenter providesMainPresenter(GetConfiguration getConfiguration, GetMovies getMovies, GetGenres getGenres, GetSearchResult getSearchResult) {
        return new MainPresenter(getConfiguration, getMovies, getGenres, getSearchResult);
    }

}