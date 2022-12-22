package com.hryshchenko.cinema.util;

import com.hryshchenko.cinema.exception.IncorrectPasswordException;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public final class PasswordHashUtil {
    private static final Argon2 argon2 = Argon2Factory.create();
    private static final int ITERATIONS = 2;
    private static final int MEMORY = 15*1024;
    private static final int PARALLELISM = 1;

    public static String encode(String password) {
        return password != null ? argon2.hash(ITERATIONS, MEMORY, PARALLELISM, password.toCharArray()) : "";
    }

    public static void verify(String hash, String password) throws IncorrectPasswordException {
        if (!argon2.verify(hash, password.toCharArray())){
            throw new IncorrectPasswordException();
        }
    }

    private PasswordHashUtil() {}
}
