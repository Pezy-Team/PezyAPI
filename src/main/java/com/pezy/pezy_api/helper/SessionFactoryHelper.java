package com.pezy.pezy_api.helper;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryHelper {
	
	private static Session fSession;

	static {
		try {
	        Configuration configuration = new Configuration();
	        configuration.configure("persistence.xml");
	        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
	        fSession = sessionFactory.openSession();
		} catch (Exception e) {
			System.out.print(String.format("Build session factory failed : %s", e.getMessage()));
		}
	}
	
	public static Session getSessionFactory() {
		return fSession;
	}
	
	public static EntityManager createEntityManager() {
		return fSession.getEntityManagerFactory().createEntityManager();
	}
	
}
