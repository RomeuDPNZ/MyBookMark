package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import mybookmark.dao.HibernateDAO;
import mybookmark.dao.Settings;

public class SelectSettings extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {      
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONArray jsonArray = new JSONArray();
		
		try {
			HibernateDAO mbmDAO = new HibernateDAO();
			
			Settings settings = (Settings) mbmDAO.select("Default", Settings.class);		
			
			JSONObject jsonSettings = new JSONObject();
			jsonSettings.put("id", settings.getId());
			jsonSettings.put("name", settings.getName());
			jsonSettings.put("description", settings.getDescription());
			jsonSettings.put("theme", settings.getTheme());
			jsonSettings.put("lastUpdatedOn", settings.getLastUpdatedOnString());
			jsonSettings.put("createdOn", settings.getCreatedOnString());
			
			jsonArray.put(jsonSettings);
			
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