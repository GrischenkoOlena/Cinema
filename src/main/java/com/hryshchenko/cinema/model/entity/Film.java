package com.hryshchenko.cinema.model.entity;

import java.util.Objects;

public class Film extends Entity{
    private static final long serialVersionUID = 1L;
    private String title;
    private String director;
    private String cast;
    private String description;
    private long genreId;
    private int duration;

    public Film(){}

    private Film (FilmBuilder filmBuilder){
        super(filmBuilder.id);
        title = filmBuilder.title;
        director = filmBuilder.director;
        cast = filmBuilder.cast;
        description = filmBuilder.description;
        genreId = filmBuilder.genreId;
        duration = filmBuilder.duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getDescription() {
        return description;
    }

    public long getGenreID() {
        return genreId;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", cast='" + cast + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genreId +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return genreId == film.genreId && duration == film.duration && Objects.equals(title, film.title)
                && Objects.equals(director, film.director) && Objects.equals(cast, film.cast)
                && Objects.equals(description, film.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director, cast, description, genreId, duration);
    }

    public static class FilmBuilder{
        private final long id;
        private String title = "";
        private String director = "";
        private String cast = "";
        private String description = "";
        private long genreId = 0;
        private int duration = 0;

        public FilmBuilder(long id) {
            this.id = id;
        }
        public FilmBuilder title (String val){
            title = val;
            return this;
        }

        public FilmBuilder director(String val){
            director = val;
            return this;
        }
        public FilmBuilder cast(String val){
            cast = val;
            return this;
        }
        public FilmBuilder description(String val){
            description = val;
            return this;
        }
        public FilmBuilder genreId(long val){
            genreId = val;
            return this;
        }
        public FilmBuilder duration (int val){
            duration = val;
            return this;
        }

        public Film build(){
            return new Film(this);
        }
    }
}
