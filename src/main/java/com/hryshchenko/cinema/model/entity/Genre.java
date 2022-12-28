package com.hryshchenko.cinema.model.entity;

public class Genre extends Entity {
    private static final long serialVersionUID = 1L;
    private String genre;

    public Genre() {}

    public Genre(long id, String genre) {
        super(id);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre='" + genre + '\'' +
                '}';
    }
}
