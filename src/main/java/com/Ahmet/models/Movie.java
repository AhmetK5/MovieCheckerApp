package com.Ahmet.models;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Movie {
    private int id;          //id of the moive
    private String title;    // the name of the movie
    @SerializedName("release_date")
    private String releaseDate;      // YYYY-MM-DD format
    @SerializedName("genre_ids")
    private List<Integer> genreIds;  //List of genre IDs associated with the movie
    @SerializedName("overview")
    private String description;      //short description of the movie

    public Movie(int id, String title, String releaseDate, List<Integer> genreIds, String description) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.description = description;
    }

    //getters
    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public List<Integer> getGenreIds() {
        return genreIds;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Movie: " +
                "title = '" + title + '\'' +
                ", releaseDate = '" + releaseDate + '\'' +
                ", genreIds = " + genreIds +
                ", overview = '" + description + '\'' +
                '!';
    }

}
