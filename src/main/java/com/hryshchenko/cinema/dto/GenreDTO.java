package com.hryshchenko.cinema.dto;

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
}
