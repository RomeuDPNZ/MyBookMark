package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONObject;

import mybookmark.dao.HibernateDAO;
import mybookmark.dao.Settings;
import mybookmark.dao.Theme;

public class EditSettings extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		
		try {
			HibernateDAO mbmDAO = new HibernateDAO();
			
			Settings settings = (Settings) mbmDAO.select("Default", Settings.class);
			
			if(settings.getTheme().equals(Theme.DARK)) {
				settings.setTheme(Theme.LIGHT);
			} else {
				settings.setTheme(Theme.DARK);
			}	
			
			mbmDAO.update(settings);
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
