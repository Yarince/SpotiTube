package nl.han.oose.yarince.datasource.JPAImpl;

import javax.persistence.EntityManagerFactory;

/**
 * Project name: SpotiTube.
 * Created by Yarince on 03/04/2017.
 */
public interface IJPAConnection {

   EntityManagerFactory getEntityManagerFactory();
}
