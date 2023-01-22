package com.hryshchenko.cinema.dto;

import java.util.Objects;

public class FilmDTO implements ISimpleDTO {
    private static final long serialVersionUID = 1L;
    private final long id;
    private final String title;
    private final String director;
    private final String cast;
    private final String description;
    private final GenreDTO genre;
    private final int duration;


    private FilmDTO (FilmDTOBuilder filmBuilder){
        id = filmBuilder.id;
        title = filmBuilder.title;
        director = filmBuilder.director;
        cast = filmBuilder.cast;
        description = filmBuilder.description;
        genre = filmBuilder.genre;
        duration = filmBuilder.duration;
    }

    public long getId() {
        return id;
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

    public GenreDTO getGenre() {
        return genre;
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
                ", genre=" + genre +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDTO filmDTO = (FilmDTO) o;
        return id == filmDTO.id && duration == filmDTO.duration
                && Objects.equals(title, filmDTO.title)
                && Objects.equals(director, filmDTO.director)
                && Objects.equals(cast, filmDTO.cast)
                && Objects.equals(description, filmDTO.description)
                && Objects.equals(genre, filmDTO.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, director, cast, description, genre, duration);
    }

    public static class FilmDTOBuilder{
        private final long id;
        private String title = "";
        private String director = "";
        private String cast = "";
        private String description = "";
        private GenreDTO genre = new GenreDTO();
        private int duration = 0;

        public FilmDTOBuilder(long id) {
            this.id = id;
        }

        public FilmDTOBuilder title (String val){
            title = val;
            return this;
        }

        public FilmDTOBuilder director(String val){
            director = val;
            return this;
        }
        public FilmDTOBuilder cast(String val){
            cast = val;
            return this;
        }
        public FilmDTOBuilder description(String val){
            description = val;
            return this;
        }
        public FilmDTOBuilder genre(GenreDTO val){
            genre = val;
            return this;
        }
        public FilmDTOBuilder duration (int val){
            duration = val;
            return this;
        }

        public FilmDTO build(){
            return new FilmDTO(this);
        }
    }
}
