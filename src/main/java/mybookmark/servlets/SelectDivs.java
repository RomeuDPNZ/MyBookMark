package mybookmark.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.*;

import org.json.JSONArray;

import mybookmark.dao.HibernateDAO;

public class SelectDivs extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {      
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		JSONArray jsonArray = new JSONArray();
		
		try {
			HibernateDAO mbmDAO = new HibernateDAO();
			
			jsonArray = mbmDAO.selectAll();
			
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