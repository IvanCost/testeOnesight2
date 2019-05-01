package com.example.testeonsigth2;

import android.app.Application;

import com.example.testeonsigth2.injection.component.AppComponent;
import com.example.testeonsigth2.injection.component.DaggerAppComponent;
import com.example.testeonsigth2.injection.module.AppModule;
import com.example.testeonsigth2.injection.module.NetModule;
import com.example.testeonsigth2.injection.module.PresentationModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initDaggerComponent();
    }

    private AppComponent initDaggerComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .presentationModule(new PresentationModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}

