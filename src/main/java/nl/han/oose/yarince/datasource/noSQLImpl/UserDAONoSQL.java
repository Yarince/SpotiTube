package nl.han.oose.yarince.datasource.noSQLImpl;

import nl.han.oose.yarince.datasource.IUserDAO;
import nl.han.oose.yarince.domain.User;

import javax.enterprise.inject.Alternative;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 03/04/2017.
 */
@Alternative
public class UserDAONoSQL implements IUserDAO {
    @Override
    public User login(User user) {
        return user;
    }
}
