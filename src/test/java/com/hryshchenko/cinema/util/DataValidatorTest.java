package com.hryshchenko.cinema.util;

import com.hryshchenko.cinema.exception.FieldValidatorException;
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
    public void validateBadLoginTest()  {
        String login = "makar@i";
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateLogin(login));
    }

    @Test
    public void validatePasswordTest() throws FieldValidatorException {
        String password = "password";
        assertTrue(DataValidator.validatePassword(password));
    }
    @Test
    public void validateBadPasswordTest() {
        String password = "2@";
        assertThrows(FieldValidatorException.class, ()->DataValidator.validatePassword(password));
    }

    @Test
    public void validateNameTest() throws FieldValidatorException {
        String name = "Ivan Євгенії";
        assertTrue(DataValidator.validateName(name));
    }
    @Test
    public void validateBadNameTest() {
        String name = "@Ivan2";
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateName(name));
    }

    @Test
    public void validateTitleTest() throws FieldValidatorException {
        String title = "Avatar: Путь воды";
        assertTrue(DataValidator.validateTitle(title));
    }
    @Test
    public void validateBadTitleTest() {
        String title = "@";
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateTitle(title));
    }

    @Test
    public void validateDirectorTest() throws FieldValidatorException {
        String director = "James Cameron";
        assertTrue(DataValidator.validateDirector(director));
    }
    @Test
    public void validateBadDirectorTest() {
        String director = "2";
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateDirector(director));
    }

    @Test
    public void validateCastTest() throws FieldValidatorException {
        String cast = "Leonardo di Caprio, Samuil Jon";
        assertTrue(DataValidator.validateCast(cast));
    }
    @Test
    public void validateBadCastTest() {
        String cast = "Sam";
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateCast(cast));
    }

    @Test
    public void validateDurationTest() throws FieldValidatorException {
        int duration = 15;
        assertTrue(DataValidator.validateDuration(duration));
    }
    @Test
    public void validateBadDurationTest() {
        int duration = 5;
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateDuration(duration));
    }

    @Test
    public void validateBalanceTest() throws FieldValidatorException {
        double balance = 250.0;
        assertTrue(DataValidator.validateBalance(balance));
    }
    @Test
    public void validateBadBalanceTest() {
        double balance = 25000.0;
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateBalance(balance));
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateBalance(-1.0));
    }


    @Test
    public void validateDateScreeningTest() throws FieldValidatorException {
        LocalDate dateUpdateBad = LocalDate.parse("2023-01-19");
        assertThrows(FieldValidatorException.class, ()->DataValidator.validateDateScreening(dateUpdateBad));

        LocalDate dateUpdate = LocalDate.now();
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
