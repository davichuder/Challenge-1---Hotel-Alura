package com.challenge.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hotelaluradb");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}