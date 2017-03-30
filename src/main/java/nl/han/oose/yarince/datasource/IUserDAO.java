package nl.han.oose.yarince.datasource;

import nl.han.oose.yarince.domain.User;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 27/03/2017.
 */
public interface IUserDAO {
    User login(User user);
}
