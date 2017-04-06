package nl.han.oose.yarince.datasource;

import java.sql.Connection;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 20/03/2017.
 */
public interface IConnection {
    Connection getConnection();
}
