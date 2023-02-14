package com.hryshchenko.cinema.controller.commandFactory;

import com.hryshchenko.cinema.controller.commands.admin.*;
import com.hryshchenko.cinema.controller.commands.common.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommandFactoryTest {
    @Mock
    HttpServletRequest req;
    CommandFactory commandFactory = CommandFactory.commandFactory();

    public static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("empty", EmptyCommand.class),
                Arguments.of("main", MainCommand.class),
                Arguments.of("login", LoginCommand.class),
                Arguments.of("signUp", SignUpCommand.class),
                Arguments.of("freeSeats", FreeSeatCommand.class),
                Arguments.of("logout", LogoutCommand.class),
                Arguments.of("profile", ProfileCommand.class),
                Arguments.of("customers", CustomersCommand.class),
                Arguments.of("screenings", ScreeningsCommand.class),
                Arguments.of("films", FilmsCommand.class),
                Arguments.of("addFilm", AddFilmCommand.class),
                Arguments.of("updateFilm", UpdateFilmCommand.class)
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void getCommandTest(String in, Class out) {
        Mockito.when(req.getParameter("action")).thenReturn(in);
        assertEquals(out, commandFactory.getCommand(req).getClass());
    }
}