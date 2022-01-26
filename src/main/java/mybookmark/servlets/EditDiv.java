package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONObject;

import mybookmark.dao.Divs;
import mybookmark.dao.HibernateDAO;

public class EditDiv extends HttpServlet {
	
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
			
			Divs divs = (Divs) mbmDAO.select(id, Divs.class);
			
			divs.setName(name);
			divs.setDescription(description);
			divs.setOrderOf(orderOf);
			
			mbmDAO.update(divs);
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