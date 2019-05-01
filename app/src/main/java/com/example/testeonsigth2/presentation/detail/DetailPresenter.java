package com.example.testeonsigth2.presentation.detail;

import android.util.Log;

import com.example.testeonsigth2.data.model.Config;
import com.example.testeonsigth2.data.model.Imagens;
import com.example.testeonsigth2.data.model.Filme;
import com.example.testeonsigth2.data.usecase.GetConfiguration;
import com.example.testeonsigth2.data.usecase.GetMovie;

import javax.inject.Inject;

import rx.Subscriber;

public class DetailPresenter implements DetailContract.Presenter {

    private static final String TAG = "DetailPresenter";

    private GetConfiguration getConfiguration;
    private GetMovie getMovie;

    private DetailContract.View view;
    private Imagens imagens;

    @Inject
    public DetailPresenter(GetConfiguration getConfiguration, GetMovie getMovie) {
        this.getConfiguration = getConfiguration;
        this.getMovie = getMovie;
    }

    @Override
    public void start(int movieId) {
        view.showLoading();

        if (imagens == null) {
            loadConfiguration(movieId);
        } else {
            view.onConfigurationSet(imagens);
            loadMovie(movieId);
        }
    }

    @Override
    public void onViewResumed(DetailContract.View view) {
        attachedView(view);
    }

    private void attachedView(DetailContract.View view) {
        this.view = view;
    }

    private void loadConfiguration(final int movieId) {
        getConfiguration.execute().subscribe(new Subscriber<Config>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: loadConfigurations");
            }
            @Override
            public void onError(Throwable e) {
                view.showError();
                defaultErrorHandling(e);
            }
            @Override
            public void onNext(Config configuration) {
                if (hasViewAttached()) {
                    view.onConfigurationSet(configuration.imagens);
                    loadMovie(movieId);
                } else {
                    view.showError();
                }
            }
        });
    }

    private void loadMovie(int movieId) {
        getMovie.execute(movieId).subscribe(new Subscriber<Filme>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: loadMovie");
            }
            @Override
            public void onError(Throwable e) {
                defaultErrorHandling(e);
                view.showError();
            }
            @Override
            public void onNext(Filme filme) {
                if (hasViewAttached()) {
                    view.showContent(filme);
                } else {
                    view.showError();
                }
            }
        });
    }

    @Override
    public void onViewPaused(DetailContract.View view) {
        detachView();
    }

    private void detachView() {
        this.view = null;
    }

    private void defaultErrorHandling(Throwable e) {
        Log.e(TAG, Log.getStackTraceString(e));
    }


    private boolean hasViewAttached() {
        return view != null;
    }

}
