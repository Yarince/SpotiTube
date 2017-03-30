package nl.han.oose.yarince.datasource.mySQLImpl;

import nl.han.oose.yarince.datasource.IConnection;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Yarince on 20/03/2017.
 */
@Default
public class MySQLConnection implements IConnection {

    private static final String MYSQL_JDBC_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String ITEM_DB_CONNECTION_STRING = "jdbc:mysql://127.0.0.1:3306/spotitube";
    private static final String DB_USER = "java";
    private static final String DB_PASSWORD = "password";

    static {
        try {
            Class.forName(MYSQL_JDBC_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(ITEM_DB_CONNECTION_STRING, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
