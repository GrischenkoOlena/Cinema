package com.hryshchenko.cinema.util;

import com.hryshchenko.cinema.exception.FieldValidatorException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class DataValidator {
    private final static String REGEX_CHECK_FOR_LOGIN_AS_EMAIL = "^([\\w\\-\\.]+)@([\\w\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private final static String REGEX_CHECK_FOR_PASSWORD = "^([\\wа-яА-Я]{4,10})$";
    private final static String REGEX_CHECK_FOR_NAME = "^[a-zA-ZЄ-Яа-ї' ]{2,120}$";
    private final static String REGEX_CHECK_FOR_TITLE = "^.{2,250}";
    private final static String REGEX_CHECK_FOR_DIRECTOR = "^.{2,250}";
    private final static String REGEX_CHECK_FOR_CAST = "^.{5,400}";


    private final static Integer MIN_DURATION = 10;
    private final static Integer MAX_DURATION = 360;
    private final static Integer MIN_BALANCE = 0;
    private final static Integer MAX_BALANCE = 2000;
    private final static LocalTime MIN_TIME_SCREENING = LocalTime.of(8, 59);
    private final static LocalTime MAX_TIME_SCREENING = LocalTime.of(22, 1);
    private final static LocalDate CURRENT_DATE = LocalDate.now().minusDays(1);

    public static boolean validateLogin(String login) throws FieldValidatorException {
        if (!Pattern.matches(REGEX_CHECK_FOR_LOGIN_AS_EMAIL, login))
            throw new FieldValidatorException("don't correct login");
        return true;
    }

    public static boolean validatePassword(String password) throws FieldValidatorException {
        if(!Pattern.matches(REGEX_CHECK_FOR_PASSWORD, password))
            throw new FieldValidatorException("don't correct password");
        return true;
    }

    public static boolean validateName(String name) throws FieldValidatorException {
        if(!Pattern.matches(REGEX_CHECK_FOR_NAME, name))
            throw new FieldValidatorException("don't correct name");
        return true;
    }

    public static boolean validateTitle(String title) throws FieldValidatorException {
        if(!Pattern.matches(REGEX_CHECK_FOR_TITLE, title))
            throw new FieldValidatorException("don't correct title movie");
        return true;
    }

    public static boolean validateDirector(String director) throws FieldValidatorException {
        if(!Pattern.matches(REGEX_CHECK_FOR_DIRECTOR, director))
            throw new FieldValidatorException("don't correct director movie");
        return true;
    }

    public static boolean validateCast(String cast) throws FieldValidatorException {
        if(!Pattern.matches(REGEX_CHECK_FOR_CAST, cast))
            throw new FieldValidatorException("don't correct cast movie");
        return true;
    }

    public static boolean validateDuration(String durationString) throws FieldValidatorException {
        int duration;
        try {
            duration = Integer.parseInt(durationString);
        } catch (NumberFormatException e) {
            throw new FieldValidatorException("field duration contents bad symbols");
        }
        if(!(duration >= MIN_DURATION && duration <= MAX_DURATION))
            throw new FieldValidatorException("don't correct duration movie");
        return true;
    }

    public static boolean validateBalance(String balanceString) throws FieldValidatorException {
        double balance;
        try {
            balance = Double.parseDouble(balanceString);
        } catch (NumberFormatException e) {
            throw new FieldValidatorException("field balance contents bad symbols");
        }

        if(!(balance >= MIN_BALANCE && balance <= MAX_BALANCE))
            throw new FieldValidatorException("don't correct balance user");
        return true;
    }

    public static boolean validateTimeScreening(LocalTime time) throws FieldValidatorException{
        if(!(time.isAfter(MIN_TIME_SCREENING) && time.isBefore(MAX_TIME_SCREENING)))
            throw new FieldValidatorException("don't correct time session");
        return true;
    }

    public static boolean validateDateScreening(LocalDate date) throws FieldValidatorException {
        if(!date.isAfter(CURRENT_DATE))
            throw new FieldValidatorException("don't correct date session");
        return true;
    }
}
