package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import mybookmark.dao.HibernateDAO;
import mybookmark.dao.AnnualReminder;
import mybookmark.dao.Categories;
import mybookmark.dao.Chronometer;
import mybookmark.dao.Countdown;
import mybookmark.dao.Divs;
import mybookmark.dao.Links;
import mybookmark.dao.MonthlyReminder;

public class SelectDivs extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {      
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONArray jsonArray = new JSONArray();
		
		try {
			HibernateDAO mbmDAO = new HibernateDAO();
			
			jsonArray = mbmDAO.selectAll();
			
			/*
			List<Divs> divsList = (List) mbmDAO.selectAll(Divs.class);		
			
			for (Divs d : divsList) {
				JSONObject jsonDivs = new JSONObject();
				jsonDivs.put("id", d.getId());
				jsonDivs.put("name", d.getName());
				jsonDivs.put("description", d.getDescription());
				jsonDivs.put("position", d.getPosition());
				jsonDivs.put("lastUpdatedOn", d.getLastUpdatedOnString());
				jsonDivs.put("createdOn", d.getCreatedOnString());
		        
		        List<Categories> categoriesList = (List) mbmDAO.selectAllCategoriesFromDivs(d.getId(), Categories.class);
		        
		        for (Categories c : categoriesList) {
		        	JSONObject jsonCategories = new JSONObject();
					jsonCategories.put("id", c.getId());
					jsonCategories.put("div", c.getDivs());
					jsonCategories.put("name", c.getName());
					jsonCategories.put("description", c.getDescription());
					jsonCategories.put("lastUpdatedOn", c.getLastUpdatedOnString());
					jsonCategories.put("createdOn", c.getCreatedOnString());
			        
					List<Links> linksList = (List) mbmDAO.selectAllFromCategories(c.getId(), Links.class);
			        
			        for (Links l : linksList) {
			        	JSONObject jsonLinks = new JSONObject();
						jsonLinks.put("id", l.getId());
						jsonLinks.put("category", l.getCategory());
						jsonLinks.put("name", l.getName());
						jsonLinks.put("link", l.getLink());
						jsonLinks.put("description", l.getDescription());
						jsonLinks.put("lastUpdatedOn", l.getLastUpdatedOnString());
						jsonLinks.put("createdOn", l.getCreatedOnString());
				        jsonCategories.append("links", jsonLinks);
			        }
			        
			        List<Chronometer> chronometerList = (List) mbmDAO.selectAllFromCategories(c.getId(), Chronometer.class);
			        
			        for (Chronometer l : chronometerList) {
			        	JSONObject jsonChronometer = new JSONObject();
						jsonChronometer.put("id", l.getId());
						jsonChronometer.put("category", l.getCategory());
						jsonChronometer.put("name", l.getName());
						jsonChronometer.put("dateOf", l.getDateOfString());
						jsonChronometer.put("description", l.getDescription());
						jsonChronometer.put("lastUpdatedOn", l.getLastUpdatedOnString());
						jsonChronometer.put("createdOn", l.getCreatedOnString());
				        jsonCategories.append("chronometer", jsonChronometer);
			        }
			        
			        List<Countdown> countdownList = (List) mbmDAO.selectAllFromCategories(c.getId(), Countdown.class);
			        
			        for (Countdown l : countdownList) {
			        	JSONObject jsonCountdown = new JSONObject();
						jsonCountdown.put("id", l.getId());
						jsonCountdown.put("category", l.getCategory());
						jsonCountdown.put("name", l.getName());
						jsonCountdown.put("dateOf", l.getDateOfString());
						jsonCountdown.put("description", l.getDescription());
						jsonCountdown.put("lastUpdatedOn", l.getLastUpdatedOnString());
						jsonCountdown.put("createdOn", l.getCreatedOnString());
				        jsonCategories.append("countdown", jsonCountdown);
			        }
			        
			        List<AnnualReminder> annualReminderList = (List) mbmDAO.selectAllFromCategories(c.getId(), AnnualReminder.class);
			        
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
				        jsonCategories.append("annualReminder", jsonAnnualReminder);
			        }
			        
			        List<MonthlyReminder> monthlyReminderList = (List) mbmDAO.selectAllFromCategories(c.getId(), MonthlyReminder.class);
			        
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
				        jsonCategories.append("monthlyReminder", jsonMonthlyReminder);
			        }
			        
			        jsonDivs.append("categories", jsonCategories);
		        }
		        
		        jsonArray.put(jsonDivs);
			}
			*/
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		out.print(jsonArray.toString());
    }
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doPost(req, res);
	}
	
}