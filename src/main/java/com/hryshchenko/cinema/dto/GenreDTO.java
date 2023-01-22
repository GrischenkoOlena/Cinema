package com.hryshchenko.cinema.dto;

import java.util.Objects;

public class GenreDTO implements ISimpleDTO {
    private static final long serialVersionUID = 1L;
    private long id;
    private String genre;

    public GenreDTO() {}

    public GenreDTO(long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        GenreDTO genreDTO = (GenreDTO) o;
        return id == genreDTO.id && Objects.equals(genre, genreDTO.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre);
    }
}
