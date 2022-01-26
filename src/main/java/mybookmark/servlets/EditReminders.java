package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONObject;

import mybookmark.dao.AnnualReminder;
import mybookmark.dao.HibernateDAO;
import mybookmark.dao.MonthlyReminder;

public class EditReminders extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
    	Long idReminder = Long.parseLong(req.getParameter("idReminders"));
    	Long orderOf = Long.parseLong(req.getParameter("orderOf"));
    	String name = req.getParameter("name");
    	Long day = Long.parseLong(req.getParameter("day"));
    	Long month = Long.parseLong(req.getParameter("month"));
    	Long daysBefore = Long.parseLong(req.getParameter("daysBefore"));
    	Long daysAfter = Long.parseLong(req.getParameter("daysAfter"));
    	String description = req.getParameter("description");
    	String typeClass = req.getParameter("typeClass");
    	
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		
		try {
			HibernateDAO mbmDAO = new HibernateDAO();
			
			if(typeClass.equals("Annual")) {
				AnnualReminder ar = (AnnualReminder) mbmDAO.select(idReminder, AnnualReminder.class);
				
				ar.setDay(day);
				ar.setMonth(month);
				ar.setDaysBefore(daysBefore);
				ar.setDaysAfter(daysAfter);
				ar.setName(name);
				ar.setDescription(description);
				ar.setOrderOf(orderOf);
				
				mbmDAO.update(ar);
			} else if(typeClass.equals("Monthly")) {
				MonthlyReminder mr = (MonthlyReminder) mbmDAO.select(idReminder, MonthlyReminder.class);
				
				mr.setDay(day);
				mr.setDaysBefore(daysBefore);
				mr.setDaysAfter(daysAfter);
				mr.setName(name);
				mr.setDescription(description);
				mr.setOrderOf(orderOf);
				
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