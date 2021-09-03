package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;
import mybookmark.dao.Categories;
import mybookmark.dao.HibernateDAO;

import org.json.JSONObject;

public class InsertCategory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	
    	Long id = Long.parseLong(req.getParameter("id"));
		
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		
		try {
			
			HibernateDAO mbmDAO = new HibernateDAO();
			
			Categories categories = new Categories("New Category for Editing", id);
			
			mbmDAO.insert(categories);
			
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