package com.example.testeonsigth2.injection.component;

import com.example.testeonsigth2.data.remote.ApiService;
import com.example.testeonsigth2.injection.module.AppModule;
import com.example.testeonsigth2.injection.module.NetModule;
import com.example.testeonsigth2.injection.module.PresentationModule;
import com.example.testeonsigth2.presentation.detail.DetailActivity;
import com.example.testeonsigth2.presentation.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, PresentationModule.class})
public interface AppComponent {

    void inject(DetailActivity detailActivity);

    void inject(MainActivity mainActivity);

    ApiService apiService();

}

