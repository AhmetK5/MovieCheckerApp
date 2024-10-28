package com.Ahmet.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.Ahmet.models.Movie;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.Ahmet.models.Genre;

public class MovieService {
    private static final String API_KEY = "8fd263d940ca44a83f87d3dbdece144c";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private OkHttpClient client;
    private Map<Integer, String> genreMap;

    public MovieService() {
        this.client = new OkHttpClient();
        this.genreMap = new HashMap<>();
        initializeGenreMap();
    }
    //initialize the genreMap with all the id values and its pairs
    private void initializeGenreMap() {
        try{
            List<Genre> genres = getGenres();
            for (Genre genre : genres) {
                genreMap.put(genre.getId(), genre.getName());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //have the genre names show up instead of the id values
    public String getGenreNames(List<Integer> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) {
            return "No genres available";
        }
        return genreIds.stream()
                .map(id -> genreMap.getOrDefault(id, "Unknown"))
                .collect(Collectors.joining(", "));
    }

    public List<Movie> searchMovies(String query) throws IOException {
        String url = BASE_URL + "search/movie?api_key=" + API_KEY + "&query=" + query;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Parse JSON response into a list of Movie objects
            String jsonResponse = response.body().string();
            JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);

            // Extract "results" array from the response
            return new Gson().fromJson(jsonObject.getAsJsonArray("results"), new TypeToken<List<Movie>>(){}.getType());
        }
    }
    public List<Genre> getGenres() throws IOException {
        String url = BASE_URL + "genre/movie/list?api_key=" + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Parse JSON response into a list of Genre objects
            String jsonResponse = response.body().string();
            JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);

            return new Gson().fromJson(jsonObject.getAsJsonArray("genres"), new TypeToken<List<Genre>>(){}.getType());
        }
    }
    public List<Movie> getNowPlayingMovies() throws IOException {
        String url = BASE_URL + "movie/now_playing?api_key=" + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String jsonResponse = response.body().string();
            JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);

            return new Gson().fromJson(jsonObject.getAsJsonArray("results"), new TypeToken<List<Movie>>(){}.getType());
        }
    }
}
