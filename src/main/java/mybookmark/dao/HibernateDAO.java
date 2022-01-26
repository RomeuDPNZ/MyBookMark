package mybookmark.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.json.JSONArray;
import org.json.JSONObject;

public class HibernateDAO {
	
	public HibernateDAO() {

	}
	
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
	
	public JSONArray selectAll() throws Exception {
		JSONArray jsonArray = new JSONArray();
		HibernateEntityManagerFactory hs = new HibernateEntityManagerFactory();
		EntityManager em = hs.getEntityManagerFactory();
		em.getTransaction().begin();
		
		String sqlQuery = "SELECT * FROM Divs";
		Query q = em.createNativeQuery(sqlQuery, Divs.class);
		List<Divs> divsList = (List) q.getResultList();		
		
		for (Divs d : divsList) {
			JSONObject jsonDivs = new JSONObject();
			jsonDivs.put("id", d.getId());
			jsonDivs.put("name", d.getName());
			jsonDivs.put("description", d.getDescription());
			jsonDivs.put("position", d.getPosition());
			jsonDivs.put("lastUpdatedOn", d.getLastUpdatedOnString());
			jsonDivs.put("createdOn", d.getCreatedOnString());
			jsonDivs.put("orderOf", d.getOrderOf());
	        
			sqlQuery = "SELECT * FROM Categories WHERE divs=?";
			q = em.createNativeQuery(sqlQuery, Categories.class);
			q.setParameter(1, d.getId());	
	        List<Categories> categoriesList = (List) q.getResultList();
	        
	        for (Categories c : categoriesList) {
	        	JSONObject jsonCategories = new JSONObject();
				jsonCategories.put("id", c.getId());
				jsonCategories.put("div", c.getDivs());
				jsonCategories.put("name", c.getName());
				jsonCategories.put("description", c.getDescription());
				jsonCategories.put("lastUpdatedOn", c.getLastUpdatedOnString());
				jsonCategories.put("createdOn", c.getCreatedOnString());
				jsonCategories.put("orderOf", c.getOrderOf());
		        
				sqlQuery = "SELECT * FROM Links WHERE category=?";
				q = em.createNativeQuery(sqlQuery, Links.class);
				q.setParameter(1, c.getId());	
				List<Links> linksList = (List) q.getResultList();
		        
		        for (Links l : linksList) {
		        	JSONObject jsonLinks = new JSONObject();
					jsonLinks.put("id", l.getId());
					jsonLinks.put("category", l.getCategory());
					jsonLinks.put("name", l.getName());
					jsonLinks.put("link", l.getLink());
					jsonLinks.put("description", l.getDescription());
					jsonLinks.put("lastUpdatedOn", l.getLastUpdatedOnString());
					jsonLinks.put("createdOn", l.getCreatedOnString());
					jsonLinks.put("orderOf", l.getOrderOf());
			        jsonCategories.append("links", jsonLinks);
		        }
		        
		        sqlQuery = "SELECT * FROM Chronometer WHERE category=?";
				q = em.createNativeQuery(sqlQuery, Chronometer.class);
				q.setParameter(1, c.getId());	
		        List<Chronometer> chronometerList = (List) q.getResultList();
		        
		        for (Chronometer l : chronometerList) {
		        	JSONObject jsonChronometer = new JSONObject();
					jsonChronometer.put("id", l.getId());
					jsonChronometer.put("category", l.getCategory());
					jsonChronometer.put("name", l.getName());
					jsonChronometer.put("dateOf", l.getDateOfString());
					jsonChronometer.put("description", l.getDescription());
					jsonChronometer.put("lastUpdatedOn", l.getLastUpdatedOnString());
					jsonChronometer.put("createdOn", l.getCreatedOnString());
					jsonChronometer.put("orderOf", l.getOrderOf());
			        jsonCategories.append("chronometer", jsonChronometer);
		        }
		        
		        sqlQuery = "SELECT * FROM Countdown WHERE category=?";
				q = em.createNativeQuery(sqlQuery, Countdown.class);
				q.setParameter(1, c.getId());	
		        List<Countdown> countdownList = (List) q.getResultList();
		        
		        for (Countdown l : countdownList) {
		        	JSONObject jsonCountdown = new JSONObject();
					jsonCountdown.put("id", l.getId());
					jsonCountdown.put("category", l.getCategory());
					jsonCountdown.put("name", l.getName());
					jsonCountdown.put("dateOf", l.getDateOfString());
					jsonCountdown.put("description", l.getDescription());
					jsonCountdown.put("lastUpdatedOn", l.getLastUpdatedOnString());
					jsonCountdown.put("createdOn", l.getCreatedOnString());
					jsonCountdown.put("orderOf", l.getOrderOf());
			        jsonCategories.append("countdown", jsonCountdown);
		        }
		        
		        sqlQuery = "SELECT * FROM AnnualReminder WHERE category=?";
				q = em.createNativeQuery(sqlQuery, AnnualReminder.class);
				q.setParameter(1, c.getId());
		        List<AnnualReminder> annualReminderList = (List) q.getResultList();
		        
		        for (AnnualReminder l : annualReminderList) {
		        	JSONObject jsonAnnualReminder = new JSONObject();
					jsonAnnualReminder.put("id", l.getId());
					jsonAnnualReminder.put("category", l.getCategory());
					jsonAnnualReminder.put("name", l.getName());
					jsonAnnualReminder.put("day", l.getDay());
					jsonAnnualReminder.put("month", l.getMonth());
					jsonAnnualReminder.put("daysBefore", l.getDaysBefore());
					jsonAnnualReminder.put("daysAfter", l.getDaysAfter());
					jsonAnnualReminder.put("description", l.getDescription());
					jsonAnnualReminder.put("lastUpdatedOn", l.getLastUpdatedOnString());
					jsonAnnualReminder.put("createdOn", l.getCreatedOnString());
					jsonAnnualReminder.put("orderOf", l.getOrderOf());
			        jsonCategories.append("annualReminder", jsonAnnualReminder);
		        }
		        
		        sqlQuery = "SELECT * FROM MonthlyReminder WHERE category=?";
				q = em.createNativeQuery(sqlQuery, MonthlyReminder.class);
				q.setParameter(1, c.getId());
		        List<MonthlyReminder> monthlyReminderList = (List) q.getResultList();
		        
		        for (MonthlyReminder l : monthlyReminderList) {
		        	JSONObject jsonMonthlyReminder = new JSONObject();
					jsonMonthlyReminder.put("id", l.getId());
					jsonMonthlyReminder.put("category", l.getCategory());
					jsonMonthlyReminder.put("name", l.getName());
					jsonMonthlyReminder.put("day", l.getDay());
					jsonMonthlyReminder.put("daysBefore", l.getDaysBefore());
					jsonMonthlyReminder.put("daysAfter", l.getDaysAfter());
					jsonMonthlyReminder.put("description", l.getDescription());
					jsonMonthlyReminder.put("lastUpdatedOn", l.getLastUpdatedOnString());
					jsonMonthlyReminder.put("createdOn", l.getCreatedOnString());
					jsonMonthlyReminder.put("orderOf", l.getOrderOf());
			        jsonCategories.append("monthlyReminder", jsonMonthlyReminder);
		        }
		        
		        jsonDivs.append("categories", jsonCategories);
	        }
	        
	        jsonArray.put(jsonDivs);
		}
		
		em.getTransaction().commit();
		em.close();
		hs.close();
		return jsonArray;
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