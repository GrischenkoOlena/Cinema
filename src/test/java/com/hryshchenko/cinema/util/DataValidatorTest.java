package com.hryshchenko.cinema.util;

import com.hryshchenko.cinema.exception.FieldValidatorException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class DataValidatorTest {

    @Test
    public void validateLoginTest() throws FieldValidatorException {
        String login = "makar@i.ua";
        assertTrue(DataValidator.validateLogin(login));
    }

    @Test
    public void validateDateScreeningTest() throws FieldValidatorException {
        LocalDate dateUpdateBad = LocalDate.parse("2023-01-19");
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateDateScreening(dateUpdateBad));

        LocalDate dateUpdate = LocalDate.parse("2023-01-21");
        assertTrue(DataValidator.validateDateScreening(dateUpdate));
    }

    @Test
    public void validateBadTimeTest(){
        LocalTime timeUpdateBefore = LocalTime.parse("08:00");
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateTimeScreening(timeUpdateBefore));

        LocalTime timeUpdateAfter = LocalTime.parse("23:00");
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateTimeScreening(timeUpdateAfter));
    }

    @Test
    public void validateTimeTest() throws FieldValidatorException{
        LocalTime timeUpdate = LocalTime.parse("09:00");
        assertTrue(DataValidator.validateTimeScreening(timeUpdate));

        timeUpdate = LocalTime.parse("22:00");
        assertTrue(DataValidator.validateTimeScreening(timeUpdate));
    }

}
