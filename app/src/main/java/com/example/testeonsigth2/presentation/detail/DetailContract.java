package com.example.testeonsigth2.presentation.detail;

import com.example.testeonsigth2.data.model.Imagens;
import com.example.testeonsigth2.data.model.Filme;

public interface DetailContract {

    public interface View {

        void showLoading();

        void showContent(Filme filme);

        void showError();

        void onConfigurationSet(Imagens imagens);

    }

    public interface Presenter {

        void start(int movieId);

        void onViewResumed(View view);

        void onViewPaused(View view);

    }

}
