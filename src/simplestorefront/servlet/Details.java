package simplestorefront.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simplestorefront.models.DBUtil;
import simplestorefront.models.StoreItem;

/**
 * Servlet implementation class Storefront
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StoreItem item;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu22";
		String user = "cs3220stu22";
		String password = "!z6j98!z";

		Connection db = null;
		
		try {
			db = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT * FROM items WHERE id=?";
			
			PreparedStatement pstmt = db.prepareStatement(sql);

			pstmt.setString(1, request.getParameter("id"));
			
			pstmt.executeQuery();
			
			ResultSet results = pstmt.getResultSet();
			
			results.next();
			item = new StoreItem(
						results.getInt(1), //id
						results.getString(2), //name
						results.getDouble(3), //price
						results.getInt(4), //quantity
						results.getString(5), //image path
						results.getString(6)
						);
			request.setAttribute("item", item);
			List<StoreItem> items = new LinkedList<StoreItem>();
			items.add(item);
			request.setAttribute("items", items);
			request.getRequestDispatcher("/WEB-INF/storefront/details.jsp").forward(request, response);
			return;
		} catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("Store");
		} finally {
			try {
				if(db != null) db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
