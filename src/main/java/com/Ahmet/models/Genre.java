package com.Ahmet.models;

public class Genre {
    private int id;             //id for the genre
    private String name;        //name of genre

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
