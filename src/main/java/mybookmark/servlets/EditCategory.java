package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONObject;

import mybookmark.dao.Categories;
import mybookmark.dao.HibernateDAO;

public class EditCategory extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
    	Long id = Long.parseLong(req.getParameter("id"));
    	Long orderOf = Long.parseLong(req.getParameter("orderOf"));
    	String name = req.getParameter("name");
    	String description = req.getParameter("description");
    	
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		
		try {
			HibernateDAO mbmDAO = new HibernateDAO();
			
			Categories categories = (Categories) mbmDAO.select(id, Categories.class);
			
			categories.setName(name);
			categories.setDescription(description);
			categories.setOrderOf(orderOf);
			
			mbmDAO.update(categories);
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
