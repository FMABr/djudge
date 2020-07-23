package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

	public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("djudgePU");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

}
