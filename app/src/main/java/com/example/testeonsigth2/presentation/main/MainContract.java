package com.example.testeonsigth2.presentation.main;

import com.example.testeonsigth2.data.model.Genero;
import com.example.testeonsigth2.data.model.Imagens;
import com.example.testeonsigth2.data.model.Filme;

import java.util.List;

public interface MainContract {

    interface View {

        void showLoading(boolean isRefresh);

        void showContent(List<Filme> filmes, boolean isRefresh, List<Genero> generoList);

        void showError();

        void showNotFoundError();

        void onConfigurationSet(Imagens imagens);

        void showGenres(List<Genero> generos);
    }

    interface Presenter {

        void onViewStarted(View view);

        void onViewPaused(View view);

        void onPullToRefresh();

        void onScrollToBottom();

        void onViewResumed(View view);

        void onSearchQueried(String query);
    }

}
