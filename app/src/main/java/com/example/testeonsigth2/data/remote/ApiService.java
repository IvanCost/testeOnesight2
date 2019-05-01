package com.example.testeonsigth2.data.remote;

import com.example.testeonsigth2.data.model.Config;
import com.example.testeonsigth2.data.model.Filme;
import com.example.testeonsigth2.data.model.Generos;
import com.example.testeonsigth2.data.model.Filmes;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("discover/movie")
    Observable<Filmes> getMovies(@Query("primary_release_date.gte") String releaseDate,
                                 @Query("sort_by") String sortBy, @Query("page") int page);

    @GET("movie/{id}")
    Observable<Filme> getMovie(@Path("id") int id);

    @GET("search/movie")
    Observable<Filmes> getSearchResult(@Query("query") String query);

    @Headers("Cache-Control: public, max-stale=2419200")
    @GET("configuration")
    Observable<Config> getConfiguration();

    @GET("genre/movie/list")
    Observable<Generos> getGenres();

}
