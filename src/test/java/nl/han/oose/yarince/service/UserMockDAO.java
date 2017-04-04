package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.IUserDAO;
import nl.han.oose.yarince.domain.User;

import javax.enterprise.inject.Alternative;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 04/04/2017.
 */
@Alternative
public class UserMockDAO implements IUserDAO {
    @Override
    public User login(User user) {
        return user;
    }
}
