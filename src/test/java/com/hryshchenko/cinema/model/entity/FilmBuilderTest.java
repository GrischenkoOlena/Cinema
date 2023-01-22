package com.hryshchenko.cinema.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmBuilderTest {
    @Test
    public void filmBuilderTest(){
        Film expectedFilm = new Film();
        Film testFilm = new Film.FilmBuilder(0)
                .title(null)
                .director(null)
                .cast(null)
                .description(null)
                .build();
        assertEquals(expectedFilm, testFilm);
    }
}
