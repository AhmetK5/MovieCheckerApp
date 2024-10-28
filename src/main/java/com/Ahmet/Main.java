package com.Ahmet;
import com.Ahmet.models.Movie;
import com.Ahmet.models.Genre;
import com.Ahmet.services.MovieService;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            MovieService movieService = new MovieService();

            // Test searchMovies
            List<Movie> movieSearchResult = movieService.searchMovies("Batman");
            System.out.println("Search Results for 'Batman':");
            for (Movie movie : movieSearchResult) {
                String g = movieService.getGenreNames(movie.getGenreIds());
                System.out.println("Movie Title: " + movie.getTitle() + ", Release Date: " + movie.getReleaseDate() + ", The Genre(s): " + g + ", Overview: " + movie.getDescription());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MovieService movieService = new MovieService();
            List<Movie> nowPlayingMovies = movieService.getNowPlayingMovies();

            System.out.println("Now Playing Movies:");
            for (Movie movie : nowPlayingMovies) {
                String gen = movieService.getGenreNames(movie.getGenreIds());
                System.out.println("Movie Title: " + movie.getTitle() + ", Release Date: " + movie.getReleaseDate() + ", The Genre(s): " + gen + ", Overview: " + movie.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}