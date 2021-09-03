package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONObject;

import mybookmark.dao.Chronometer;
import mybookmark.dao.Countdown;
import mybookmark.dao.HibernateDAO;

public class EditTimers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
    	Long idTimer = Long.parseLong(req.getParameter("idTimers"));
    	String name = req.getParameter("name");
    	String dateOf = req.getParameter("dateOf");
    	String description = req.getParameter("description");
    	String typeClass = req.getParameter("typeClass");
    	
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		
		try {
			HibernateDAO mbmDAO = new HibernateDAO();
			
			if(typeClass.equals("Chronometer")) {
				Chronometer c = (Chronometer) mbmDAO.select(idTimer, Chronometer.class);
				
				c.setDateOfString(dateOf);
				c.setName(name);
				c.setDescription(description);
				
				mbmDAO.update(c);
			} else if(typeClass.equals("Countdown")) {
				Countdown c = (Countdown) mbmDAO.select(idTimer, Countdown.class);
				
				c.setDateOfString(dateOf);
				c.setName(name);
				c.setDescription(description);
				
				mbmDAO.update(c);
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
