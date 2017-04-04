package nl.han.oose.yarince.service;

import nl.han.oose.yarince.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 04/04/2017.
 */
class UserServiceTest {

    private UserService sut;

    @BeforeEach
    void setUp() {
        sut = new UserService();
        sut.userDAO = new UserMockDAO();
    }

    @Test
    void login() {
        User expected = new User("test", "test");
        User actual = sut.login(expected);
        assertEquals(actual, expected);
    }

}