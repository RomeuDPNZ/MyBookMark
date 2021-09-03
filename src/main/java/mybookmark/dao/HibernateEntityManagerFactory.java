package mybookmark.dao;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateEntityManagerFactory {

	public EntityManagerFactory emf;
	public EntityManager em;

	public HibernateEntityManagerFactory() {
		Properties props = new Properties();
		emf = Persistence.createEntityManagerFactory("MyBookMarkPersistence", props);
		em = emf.createEntityManager();
	}
	
	public EntityManager getEntityManagerFactory() {
		return em;
	}
	
	public void close() {
		/*
		 * 
		 * Não execute em.close();
		 * 
		 */
		emf.close();
	}

}
