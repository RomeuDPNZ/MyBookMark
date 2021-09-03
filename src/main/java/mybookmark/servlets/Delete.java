package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONObject;

import mybookmark.dao.AnnualReminder;
import mybookmark.dao.Chronometer;
import mybookmark.dao.Countdown;
import mybookmark.dao.HibernateDAO;
import mybookmark.dao.Links;
import mybookmark.dao.MonthlyReminder;

public class Delete extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
    	Long idElement = Long.parseLong(req.getParameter("idElement"));
    	String typeClass = req.getParameter("typeClass");
        
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		
		try {
			HibernateDAO mbmDAO = new HibernateDAO();
			
			if(typeClass.equals("Link")) {
				mbmDAO.delete(idElement, Links.class);
			} else if(typeClass.equals("Chronometer")) {
				mbmDAO.delete(idElement, Chronometer.class);
			} else if(typeClass.equals("Countdown")) {
				mbmDAO.delete(idElement, Countdown.class);
			} else if(typeClass.equals("Annual")) {
				mbmDAO.delete(idElement, AnnualReminder.class);
			} else if(typeClass.equals("Monthly")) {
				mbmDAO.delete(idElement, MonthlyReminder.class);
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