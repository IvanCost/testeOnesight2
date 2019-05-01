package com.example.testeonsigth2.presentation.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.testeonsigth2.App;

import com.example.testeonsigth2.R;
import com.example.testeonsigth2.data.model.Imagens;
import com.example.testeonsigth2.data.model.Filme;
import com.example.testeonsigth2.util.StringFormatter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends FragmentActivity implements DetailContract.View {
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_TITLE = "movie_title";

    @Inject
    DetailContract.Presenter presenter;

    @BindView(R.id.container)
    View contentView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.overviewHeader)
    View overviewHeader;
    @BindView(R.id.overviewTextView)
    TextView overviewTextView;
    @BindView(R.id.genresTextView)
    TextView genresTextView;
    @BindView(R.id.durationTextView)
    TextView durationTextView;
    @BindView(R.id.languageTextView)
    TextView languageTextView;
    @BindView(R.id.dateTextView)
    TextView dateTextView;
    @BindView(R.id.nameTextView)
    TextView nameTextView;
    @BindView(R.id.moreInfoButton)
    Button bookButton;
    @BindView(R.id.textView)
    View errorView;
    @BindView(R.id.progressBar)
    View loadingView;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    private int movieId = -1;
    private Imagens imagens;

    private StringFormatter stringFormatter;

    public DetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ((App) getApplication()).getAppComponent().inject(this);

        stringFormatter = new StringFormatter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movieId = extras.getInt(MOVIE_ID);
            String movieTitle = extras.getString(MOVIE_TITLE);

            setTitle(movieTitle);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewResumed(this);
        presenter.start(movieId);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onViewPaused(this);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        showContent(false);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showContent(Filme filme) {
        String fullImageUrl = getFullImageUrl(filme);

        if (!fullImageUrl.isEmpty()) {
            loadPoster(fullImageUrl);
        }

        genresTextView.setText(stringFormatter.getGenres(filme.genres));
        durationTextView.setText(stringFormatter.getDuration(filme.runtime));
        languageTextView.setText(stringFormatter.getLanguages(filme.spokenLanguages));
        dateTextView.setText(stringFormatter.getReleaseDate(filme));
        nameTextView.setText(filme.title);
        overviewTextView.setText(stringFormatter.getOverview(filme.overview));

        loadingView.setVisibility(View.GONE);
        showContent(true);
        errorView.setVisibility(View.GONE);
    }

    private void loadPoster(String fullImageUrl) {
        Glide.with(this)
                .load(fullImageUrl)
                .centerCrop()
                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);
    }

    @NonNull
    private String getFullImageUrl(Filme filme) {
        String imagePath;

        if (filme.posterPath != null && !filme.posterPath.isEmpty()) {
            imagePath = filme.posterPath;
        } else {
            imagePath = filme.backdropPath;
        }

        if (imagens != null && imagens.baseUrl != null && !imagens.baseUrl.isEmpty()) {
            if (imagens.posterSizes != null) {
                if (imagens.posterSizes.size() > 4) {
                    return imagens.baseUrl + imagens.posterSizes.get(4) + imagePath;
                } else {
                    return imagens.baseUrl + "w500" + imagePath;
                }
            }
        }

        return "";
    }

    @Override
    public void showError() {
        loadingView.setVisibility(View.GONE);
        showContent(false);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onConfigurationSet(Imagens imagens) {
        this.imagens = imagens;
    }

    private void showContent(boolean show) {
        int visibility = show ? View.VISIBLE : View.INVISIBLE;

        contentView.setVisibility(visibility);
        overviewHeader.setVisibility(visibility);
        overviewTextView.setVisibility(visibility);
        bookButton.setVisibility(visibility);
    }

    @OnClick(R.id.moreInfoButton)
    void onMoreInfoButtonClick() {
        String url = getString(R.string.web_url) + movieId;
        if (Build.VERSION.SDK_INT >= 16) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        } else {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }

}
