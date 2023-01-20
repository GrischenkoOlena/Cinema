package com.hryshchenko.cinema.model.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre other = (Genre) o;
        return Objects.equals(genre, other.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre);

    }
}
