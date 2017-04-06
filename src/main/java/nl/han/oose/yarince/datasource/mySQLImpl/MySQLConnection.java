package nl.han.oose.yarince.datasource.mySQLImpl;

import javax.enterprise.inject.Default;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 20/03/2017.
 */
@Default
public class MySQLConnection implements IMySQLConnection {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(MySQLConnection.class.getClassLoader().getResourceAsStream("config.properties"));
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
