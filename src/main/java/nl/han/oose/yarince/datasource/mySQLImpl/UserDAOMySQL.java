package nl.han.oose.yarince.datasource.mySQLImpl;

import nl.han.oose.yarince.datasource.IUserDAO;
import nl.han.oose.yarince.domain.User;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 27/03/2017.
 */
public class UserDAOMySQL implements IUserDAO {

    @Inject
    private IMySQLConnection connection;

    @Override
    public User login(User user) {

        User result = null;
        try (
                Connection con = connection.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM user WHERE USERNAME = ? and PASSWORD = ?")
        ) {
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                result = getUserByResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User getUserByResultSet(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("USERNAME"), resultSet.getString("PASSWORD"));
    }
}
