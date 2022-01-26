package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONObject;

import mybookmark.dao.HibernateDAO;
import mybookmark.dao.Links;

public class EditLink extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Long idLink = Long.parseLong(req.getParameter("id"));
		Long orderOf = Long.parseLong(req.getParameter("orderOf"));
    	String name = req.getParameter("name");
    	String linkFromInput = req.getParameter("link");
    	String description = req.getParameter("description");
        
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONObject json = new JSONObject();
		
		try {
			Links link = null;
			HibernateDAO mbmDAO = new HibernateDAO();
				
			link = (Links) mbmDAO.select(idLink, Links.class);
			
			link.setName(name);
			link.setLink(linkFromInput);
			link.setDescription(description);
			link.setOrderOf(orderOf);
			
			mbmDAO.update(link);
			
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