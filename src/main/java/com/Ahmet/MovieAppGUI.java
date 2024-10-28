package com.Ahmet;

import com.Ahmet.models.Genre;
import com.Ahmet.models.Movie;
import com.Ahmet.services.MovieService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MovieAppGUI extends Application {
    private MovieService movieService;
    private ComboBox<Genre> genreComboBox;
    private ListView<String> movieListView;

    @Override
    public void start(Stage primaryStage) {
        movieService = new MovieService();
        genreComboBox = new ComboBox<>();
        genreComboBox.getItems().addAll(getGenres());

        //button for to select the genre
        Button showMoviesButton = new Button("Show Movies");
        showMoviesButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        showMoviesButton.setOnAction(e -> showMoviesByGenre());

        //set the movie as a list
        movieListView = new ListView<>();

        //vbox
        VBox layout = new VBox(10, genreComboBox, showMoviesButton, movieListView);
        genreComboBox.setPromptText("Select Genre for Current Playing Movies");
        genreComboBox.setStyle("-fx-background-color: #2196F3;");
        Scene scene = new Scene(layout, 500,600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Movies Playing Based on Genres: ");
        primaryStage.show();
    }
    private List<Genre> getGenres() {
        try {
            return movieService.getGenres();
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
    private void showMoviesByGenre() {
        Genre selectedGenre = genreComboBox.getValue();
        if(selectedGenre == null) {
            movieListView.getItems().setAll("Please select a genre.");
            return;
        }
        try{
            List<Movie> nowPlayingMoives = movieService.getNowPlayingMovies();
            List<String> filteredMovies = nowPlayingMoives.stream()
                    .filter(movie -> movie.getGenreIds() != null && movie.getGenreIds().contains(selectedGenre.getId()))
                    .map(movie -> formatMovieDetails(movie))
                    .collect(Collectors.toList());
            if(filteredMovies.isEmpty()) {
                movieListView.getItems().setAll("No movies found for this genre :(");
            }else{
                movieListView.getItems().setAll(filteredMovies);
            }
        }catch (IOException e){
            movieListView.getItems().setAll("Error fetching the movies try again.");
            e.printStackTrace();
        }
    }
    private String formatMovieDetails(Movie movie){
        return "Title: " + movie.getTitle() +
                "\nRelease Date: " + movie.getReleaseDate() +
                "\nGenres: " + movieService.getGenreNames(movie.getGenreIds()) +
                "\nOverview: " + movie.getDescription();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
