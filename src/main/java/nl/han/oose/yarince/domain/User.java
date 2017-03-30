package nl.han.oose.yarince.domain;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 27/03/2017.
 */

public class User {
    private String username,
            password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
