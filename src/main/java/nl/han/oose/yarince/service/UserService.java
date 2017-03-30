package nl.han.oose.yarince.service;

import nl.han.oose.yarince.datasource.IUserDAO;
import nl.han.oose.yarince.domain.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 27/03/2017.
 */
@Path("/login")
public class UserService {

    @Inject
    IUserDAO userDAO;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public User login(User user) {
        return userDAO.login(user);
    }
}
