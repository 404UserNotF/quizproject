package com.beaconfire.quizsystem.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtils {
    public static Session getSession(SessionFactory sessionFactory){
        return sessionFactory.openSession();
    }
}
