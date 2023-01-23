package com.hryshchenko.cinema.controller.commands;

import com.hryshchenko.cinema.constant.Path;
import com.hryshchenko.cinema.constant.enums.UserRole;
import com.hryshchenko.cinema.exception.DAOException;
import com.hryshchenko.cinema.model.dbservices.UserService;
import com.hryshchenko.cinema.model.entity.User;
import com.hryshchenko.cinema.util.PasswordHashUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginCommandTest {

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @Mock
    HttpSession session;

    @Mock
    UserService service;

    @InjectMocks
    LoginCommand command;

    User user = new User();

    @BeforeEach
    void setUp() {
        user.setLogin("gena@i.ua");
        user.setPassword(PasswordHashUtil.encode("1234"));
        user.setRole(UserRole.ADMIN);
    }

    @Test
    void executeTest() throws DAOException {
        Mockito.when(req.getParameter("login")).thenReturn("gena@i.ua");
        Mockito.when(req.getParameter("password")).thenReturn("1234");

        Mockito.when(req.getSession()).thenReturn(session);

        Mockito.when(service.getUserByLogin("gena@i.ua")).thenReturn(Optional.of(user));
        assertEquals(Path.COMMAND_REDIRECT, command.execute(req, resp));
    }

    @Test
    void executeBadLoginTest() throws DAOException {
        Mockito.when(req.getParameter("login")).thenReturn("gena@i.ua");
        Mockito.when(req.getParameter("password")).thenReturn("4569");

        Mockito.when(req.getSession()).thenReturn(session);

        Mockito.when(service.getUserByLogin("gena@i.ua")).thenReturn(Optional.of(user));
        assertEquals(Path.LOGIN, command.execute(req, resp));
    }

    @Test
    void executeUserNotFoundTest() throws DAOException {
        Mockito.when(req.getParameter("login")).thenReturn("gena@i.ua");
        Mockito.when(req.getParameter("password")).thenReturn("4569");

        Mockito.when(req.getSession()).thenReturn(session);

        Mockito.when(service.getUserByLogin("gena@i.ua")).thenReturn(Optional.empty());
        assertEquals(Path.SIGN_UP, command.execute(req, resp));
    }
}