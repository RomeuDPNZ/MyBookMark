package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONObject;

import mybookmark.dao.MyBookMark;
import mybookmark.dao.AnnualReminder;
import mybookmark.dao.Chronometer;
import mybookmark.dao.Countdown;
import mybookmark.dao.HibernateDAO;
import mybookmark.dao.Links;
import mybookmark.dao.MonthlyReminder;

public class Insert extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
    	// Long idDiv = Long.parseLong(req.getParameter("idDiv"));
    	Long idCategory = Long.parseLong(req.getParameter("idCategory"));
    	String typeClass = req.getParameter("typeClass");
    	
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		
		try {
			MyBookMark mbm = null;
			HibernateDAO mbmDAO = new HibernateDAO();
			
			if(typeClass.equals("Link")) {
				mbm = new Links("New Link For Editing", "A description", "#", idCategory);
			} else if(typeClass.equals("Chronometer")) {
				mbm = new Chronometer("New Chronometer For Editing", "A description", "1983-09-30 00:00:00", idCategory);
			} else if(typeClass.equals("Countdown")) {
				mbm = new Countdown("New Countdown For Editing", "A description", "2030-09-30 00:00:00", idCategory);
			} else if(typeClass.equals("Annual")) {
				mbm = new AnnualReminder("Annual Reminder", "A description", 1l, 1l, 5l, 5l, idCategory);
			} else if(typeClass.equals("Monthly")) {
				mbm = new MonthlyReminder("Monthly Reminder", "A description", 1l, 5l, 5l, idCategory);
			}
			
			Long idMBM = mbmDAO.insert(mbm);
			
			Links link = null;
			Chronometer ch = null;
			Countdown cd = null;
			AnnualReminder ar = null;
			MonthlyReminder mr = null;
			
			if(typeClass.equals("Link")) {
				link = (Links) mbmDAO.select(idMBM, Links.class);
				link.setOrderOf(link.getId());
				mbmDAO.update(link);
			} else if(typeClass.equals("Chronometer")) {
				ch = (Chronometer) mbmDAO.select(idMBM, Chronometer.class);
				ch.setOrderOf(ch.getId());
				mbmDAO.update(ch);
			} else if(typeClass.equals("Countdown")) {
				cd = (Countdown) mbmDAO.select(idMBM, Countdown.class);
				cd.setOrderOf(cd.getId());
				mbmDAO.update(cd);
			} else if(typeClass.equals("Annual")) {
				ar = (AnnualReminder) mbmDAO.select(idMBM, AnnualReminder.class);
				ar.setOrderOf(ar.getId());
				mbmDAO.update(ar);
			} else if(typeClass.equals("Monthly")) {
				mr = (MonthlyReminder) mbmDAO.select(idMBM, MonthlyReminder.class);
				mr.setOrderOf(mr.getId());
				mbmDAO.update(mr);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
		out.print(json.toString());
    }
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doPost(req, res);
	}
	
}
