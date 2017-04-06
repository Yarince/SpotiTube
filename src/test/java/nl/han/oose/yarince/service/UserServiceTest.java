package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.IUserDAO;
import nl.han.oose.yarince.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 06/04/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private UserService sut;

    @Test
    public void login() throws Exception {
        User user = new User("yarince", "pw");
        Mockito.when(userDAO.login(user)).thenReturn(user);
        assertEquals(user, sut.login(user));
    }
}