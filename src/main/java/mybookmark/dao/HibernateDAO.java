package mybookmark.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class HibernateDAO {
	
	public HibernateDAO() {

	}
	
	/*
	private MyBookMark getTypeFactory(Class type) throws Exception{
		
		MyBookMark mbmType = null;
		
		if(type.equals(Divs.class)) {
			mbmType = new Divs();
		} else if(type.equals(Categories.class)) {
			mbmType = new Categories();
		} else if(type.equals(Links.class)) {
			mbmType = new Links();
		} else if(type.equals(Chronometer.class)) {
			mbmType = new Chronometer();
		} else if(type.equals(Countdown.class)) {
			mbmType = new Countdown();
		} else if(type.equals(AnnualReminder.class)) {
			mbmType = new AnnualReminder();
		} else if(type.equals(MonthlyReminder.class)) {
			mbmType = new MonthlyReminder();
		} else if(type.equals(Settings.class)) {
			mbmType = new Settings();
		} else {
			throw new Exception("Unknow Class inside getTypeFactory() method inside MyBookMarkDAO class");
		}
		
		return mbmType;
	}
	*/
	
	public Long insert(Object o) throws Exception {
		Long lastInsertId = 0l;
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		em.persist(o);
		Query q = em.createNativeQuery("SELECT LAST_INSERT_ID()");
		BigInteger bi = (BigInteger) q.getSingleResult();
		lastInsertId = bi.longValue();
		em.getTransaction().commit();
		em.close();
		hs.close();
		return lastInsertId;
	}

	public Object select(Long id, Class type) throws Exception {
		Object o;
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		o = (Object) em.find(type, id);
		em.getTransaction().commit();
		em.close();
		hs.close();
		return o;
	}

	public Object select(String name, Class type) throws Exception {
		Object o;
		HibernateEntityManagerFactory hs= new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		String sqlQuery = "SELECT * FROM "+type.getSimpleName()+" WHERE name=? LIMIT 1";
		Query q = em.createNativeQuery(sqlQuery, type);
		q.setParameter(1, name);
		o = (Object) q.getSingleResult();
		em.getTransaction().commit();
		em.close();
		hs.close();
		return o;
	}
	
	public List<Object> selectAll(Class type) throws Exception {
		List<Object> myBookMarkList = new ArrayList<Object>();
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		String sqlQuery = "SELECT * FROM "+type.getSimpleName()+"";
		Query q = em.createNativeQuery(sqlQuery, type);
		myBookMarkList = (List<Object>) q.getResultList();
		em.getTransaction().commit();
		em.close();
		hs.close();
		return myBookMarkList;
	}
	
	public List<Object> selectAllCategoriesFromDivs(Long id, Class type) throws Exception {
		List<Object> myBookMarkList = new ArrayList<Object>();
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		String sqlQuery = "SELECT * FROM "+type.getSimpleName()+" WHERE divs=?";
		Query q = em.createNativeQuery(sqlQuery, type);
		q.setParameter(1, id);
		myBookMarkList = (List<Object>) q.getResultList();
		em.getTransaction().commit();
		em.close();
		hs.close();
		return myBookMarkList;
	}
	
	public List<Object> selectAllFromCategories(Long id, Class type) throws Exception {
		List<Object> myBookMarkList = new ArrayList<Object>();
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		String sqlQuery = "SELECT * FROM "+type.getSimpleName()+" WHERE category=?";
		Query q = em.createNativeQuery(sqlQuery, type);
		q.setParameter(1, id);
		myBookMarkList = (List<Object>) q.getResultList();
		em.getTransaction().commit();
		em.close();
		hs.close();
		return myBookMarkList;
	}
	
	public List<Object> selectAll(String name, Class type) throws Exception {
		List<Object> myBookMarkList = new ArrayList<Object>();
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		String sqlQuery = "SELECT * FROM "+type.getSimpleName()+" WHERE name=?";
		Query q = em.createNativeQuery(sqlQuery, type);
		q.setParameter(1, name);
		myBookMarkList = (List<Object>) q.getResultList();
		em.getTransaction().commit();
		em.close();
		hs.close();
		return myBookMarkList;
	}

	public Long doesExists(String name, Class type) {
		Long n = 0l;
		try {
			HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
			EntityManager em = hs.getEntityManagerFactory();
			em.getTransaction().begin();
			String sqlQuery = "SELECT COUNT(*) as n FROM "+type.getSimpleName()+" WHERE name=?";
			Query q = em.createNativeQuery(sqlQuery);
			q.setParameter(1, name);
			BigInteger bi = (BigInteger) q.getSingleResult();
			n = bi.longValue();
			em.getTransaction().commit();
			em.close();
			hs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	public void update(Object o) throws Exception {
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		em.merge(o);
		em.getTransaction().commit();
		em.close();
		hs.close();
	}

	public void delete(Long id, Class type) throws Exception {
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		em.remove(em.find(type, id));
		em.getTransaction().commit();
		em.close();
		hs.close();
	}
	
	public void deleteDiv(Long id) throws Exception {
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		
		em.remove(em.find(Divs.class, id));
		
		List<Categories> myBookMarkList = new ArrayList<Categories>();
		String sqlQuery = "SELECT * FROM Categories WHERE divs=?";
		Query q = em.createNativeQuery(sqlQuery, Categories.class);
		q.setParameter(1, id);
		myBookMarkList = (List<Categories>) q.getResultList();
		
		for (Categories c : myBookMarkList) {
        	em.remove(em.find(Categories.class, c.getId()));
        	
        	sqlQuery = "SELECT * FROM Links WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, Links.class);
    		q.setParameter(1, id);
    		List<Links> linksList = (List<Links>) q.getResultList();
	        
	        for (Links l : linksList) {
	        	em.remove(em.find(Links.class, l.getId()));
	        }
	        
	        sqlQuery = "SELECT * FROM Chronometer WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, Chronometer.class);
    		q.setParameter(1, id);
	        List<Chronometer> chronometerList = (List<Chronometer>) q.getResultList();
	        
	        for (Chronometer l : chronometerList) {
	        	em.remove(em.find(Chronometer.class, l.getId()));
	        }
	        
	        sqlQuery = "SELECT * FROM Countdown WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, Countdown.class);
    		q.setParameter(1, id);
	        List<Countdown> countdownList = (List<Countdown>) q.getResultList();
	        
	        for (Countdown l : countdownList) {
	        	em.remove(em.find(Countdown.class, l.getId()));
	        }
	        
	        sqlQuery = "SELECT * FROM AnnualReminder WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, AnnualReminder.class);
    		q.setParameter(1, id);
	        List<AnnualReminder> annualReminderList = (List<AnnualReminder>) q.getResultList();
	        
	        for (AnnualReminder l : annualReminderList) {
	        	em.remove(em.find(AnnualReminder.class, l.getId()));
	        }
	        
	        sqlQuery = "SELECT * FROM MonthlyReminder WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, MonthlyReminder.class);
    		q.setParameter(1, id);
	        List<MonthlyReminder> monthlyReminderList = (List<MonthlyReminder>) q.getResultList();
	        
	        for (MonthlyReminder l : monthlyReminderList) {
	        	em.remove(em.find(MonthlyReminder.class, l.getId()));
	        }
	        
        }
		
		em.getTransaction().commit();
		em.close();
		hs.close();
	}

	public void deleteCategory(Long id) throws Exception {
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		
		List<Categories> myBookMarkList = new ArrayList<Categories>();
		String sqlQuery = "SELECT * FROM Categories WHERE id=?";
		Query q = em.createNativeQuery(sqlQuery, Categories.class);
		q.setParameter(1, id);
		myBookMarkList = (List<Categories>) q.getResultList();
		
		for (Categories c : myBookMarkList) {
        	em.remove(em.find(Categories.class, c.getId()));
        	
        	sqlQuery = "SELECT * FROM Links WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, Links.class);
    		q.setParameter(1, id);
    		List<Links> linksList = (List<Links>) q.getResultList();
	        
	        for (Links l : linksList) {
	        	em.remove(em.find(Links.class, l.getId()));
	        }
	        
	        sqlQuery = "SELECT * FROM Chronometer WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, Chronometer.class);
    		q.setParameter(1, id);
	        List<Chronometer> chronometerList = (List<Chronometer>) q.getResultList();
	        
	        for (Chronometer l : chronometerList) {
	        	em.remove(em.find(Chronometer.class, l.getId()));
	        }
	        
	        sqlQuery = "SELECT * FROM Countdown WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, Countdown.class);
    		q.setParameter(1, id);
	        List<Countdown> countdownList = (List<Countdown>) q.getResultList();
	        
	        for (Countdown l : countdownList) {
	        	em.remove(em.find(Countdown.class, l.getId()));
	        }
	        
	        sqlQuery = "SELECT * FROM AnnualReminder WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, AnnualReminder.class);
    		q.setParameter(1, id);
	        List<AnnualReminder> annualReminderList = (List<AnnualReminder>) q.getResultList();
	        
	        for (AnnualReminder l : annualReminderList) {
	        	em.remove(em.find(AnnualReminder.class, l.getId()));
	        }
	        
	        sqlQuery = "SELECT * FROM MonthlyReminder WHERE category=?";
    		q = em.createNativeQuery(sqlQuery, MonthlyReminder.class);
    		q.setParameter(1, id);
	        List<MonthlyReminder> monthlyReminderList = (List<MonthlyReminder>) q.getResultList();
	        
	        for (MonthlyReminder l : monthlyReminderList) {
	        	em.remove(em.find(MonthlyReminder.class, l.getId()));
	        }
	        
        }
		
		em.getTransaction().commit();
		em.close();
		hs.close();
	}
	
	public Long getCount(Class type) throws Exception {
		Long count = 0l;
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		String sqlQuery = "SELECT COUNT(*) FROM "+type.getSimpleName()+"";
		Query q = em.createNativeQuery(sqlQuery);
		BigInteger bi = (BigInteger) q.getSingleResult();
		count = bi.longValue();
		em.getTransaction().commit();
		em.close();
		hs.close();
		return count;
	}

}