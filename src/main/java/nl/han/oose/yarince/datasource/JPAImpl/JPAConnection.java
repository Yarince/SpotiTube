package nl.han.oose.yarince.datasource.JPAImpl;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 20/03/2017.
 */
@Default
public class JPAConnection implements IJPAConnection {

    private static Properties properties = new Properties();

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory( "spotitubePersistenceUnit" );
    }
}
