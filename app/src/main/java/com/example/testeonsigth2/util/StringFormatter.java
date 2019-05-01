package com.example.testeonsigth2.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.example.testeonsigth2.R;
import com.example.testeonsigth2.data.model.Generos;
import com.example.testeonsigth2.data.model.Filme;
import com.example.testeonsigth2.data.model.Idioma;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StringFormatter {

    private Context context;

    public StringFormatter(Context context) {
        this.context = context;
    }

    public String getReleaseDate(Filme filme) {
        if (filme.releaseDate.equals("")) {
            return "...";
        }
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = form.parse(filme.releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        return postFormatter.format(date);
    }

    public String getLanguages(List<Idioma> spokenLanguages) {
        String languages = "";
        for (int i = 0; i < spokenLanguages.size(); i++) {
            Idioma language = spokenLanguages.get(i);
            languages += language.name + ", ";
        }

        languages = removeTrailingComma(languages);

        return languages.isEmpty() ? "-" : languages;
    }

    @NonNull
    private String removeTrailingComma(String text) {
        text = text.trim();
        if (text.endsWith(",")) {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    public String getGenres(List<Generos> genres) {
        if (genres.isEmpty()) {
            return context.getResources().getString(R.string.genres_error);
        }
        String genresString = "";
        for (int i = 0; i < genres.size(); i++) {
            Generos genre = genres.get(i);
            genresString += genre.name + ", ";
        }

        genresString = removeTrailingComma(genresString);

        return genresString.isEmpty() ? "-" : genresString;
    }

    public String getDuration(int runtime) {
        return runtime <= 0 ? "-" : context.getResources().getQuantityString(R.plurals.duration, runtime, runtime);
    }

    public String getOverview(String overview) {
        return TextUtils.isEmpty(overview) ? "-" : overview;
    }

}
