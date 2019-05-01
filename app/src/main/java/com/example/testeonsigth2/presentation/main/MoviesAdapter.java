package com.example.testeonsigth2.presentation.main;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.testeonsigth2.R;
import com.example.testeonsigth2.data.model.Filme;
import com.example.testeonsigth2.data.model.Genero;
import com.example.testeonsigth2.data.model.Imagens;
import com.example.testeonsigth2.util.StringFormatter;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Filme> filmes;
    private Activity activity;
    private Imagens imagens;
    private ItemClickListener itemClickListener;
    private List<Genero> generos;
    private StringFormatter stringFormatter;


    MoviesAdapter(List<Filme> filmes, Activity activity, Imagens imagens, ItemClickListener itemClickListener, List<Genero> generoList) {
        this.filmes = filmes;
        this.activity = activity;
        this.imagens = imagens;
        this.itemClickListener = itemClickListener;
        this.generos = generoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        stringFormatter = new StringFormatter(activity);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Filme filme = filmes.get(position);

        String fullImageUrl = getFullImageUrl(filme);
        if (!fullImageUrl.isEmpty()) {
            Glide.with(activity)
                    .load(fullImageUrl)
                    .centerCrop()
                    .crossFade()
                    .into(holder.imageView);
        }


        holder.releaseTextView.setText(stringFormatter.getReleaseDate(filme));
        holder.titleTextView.setText(filme.title);
        holder.genreTextView.setText(getMainGenres(filme));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(filme.id, filme.title);
            }
        });
    }

    private String getMainGenres(Filme filme) {
        if (filme.genresId.isEmpty()) {
            return activity.getResources().getString(R.string.genres_error);
        }
        List<String> genresString = new ArrayList<>();
        for (Genero genero : generos) {
            for (Integer id : filme.genresId) {
                if (genero.id.equals(id)) {
                    genresString.add(genero.name);
                }
            }
        }
        return StringUtils.join(genresString, ", ");
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
    public int getItemCount() {
        return filmes.size();
    }

    public void clear() {
        filmes.clear();
    }

    public void addAll(List<Filme> filmes) {
        this.filmes.addAll(filmes);
    }

    public void setImagens(Imagens imagens) {
        this.imagens = imagens;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.releaseTextView)
        TextView releaseTextView;
        @BindView(R.id.titleTextView)
        TextView titleTextView;
        @BindView(R.id.genreTextView)
        TextView genreTextView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

    }

    interface ItemClickListener {

        void onItemClick(int movieId, String title);

    }

}
