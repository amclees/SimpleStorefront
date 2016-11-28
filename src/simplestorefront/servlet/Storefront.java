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

import simplestorefront.models.Cart;
import simplestorefront.models.StoreItem;

/**
 * Servlet implementation class Storefront
 */
@WebServlet("/Storefront")
public class Storefront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StoreItem> items = new LinkedList<StoreItem>();
		
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
			
			String sql = "SELECT * FROM items";
			
			PreparedStatement pstmt = db.prepareStatement(sql);

			pstmt.executeQuery();
			
			ResultSet results = pstmt.getResultSet();
			
			while(results.next()) {
				items.add(new StoreItem(
						results.getInt(1), //id
						results.getString(2), //name
						results.getDouble(3), //price
						results.getInt(4), //quantity
						results.getString(5) //image path
						));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db != null) db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("items", items);
		request.getRequestDispatcher("/WEB-INF/dummystore.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String toAddId = request.getParameter("id");
		if(toAddId == null) {
			doGet(request, response);
			return;
		}
		int quantity = 0;
		try{
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch(Exception e) {
			doGet(request, response);
			return;
		}
		
		boolean add = true;
		try {
			add = Boolean.parseBoolean(request.getParameter("cart"));
		} catch(Exception e) {
			doGet(request, response);
			return;
		}
		
		Cart cart = new Cart();
		if(request.getSession().getAttribute("cart") != null) {
			cart = (Cart)request.getSession().getAttribute("cart");
		}			
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu22";
		String user = "cs3220stu22";
		String password = "!z6j98!z";

		Connection db = null;
		
		int id = -1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch(Exception e) {}
		
		String state = request.getParameter("state");
		if(state == null) {
			response.sendRedirect("Todo");
		}  else if((!state.equals("done") && !state.equals("notdone"))) {
			response.sendRedirect("Todo");			
		}
		
		try {
			db = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT * FROM items WHERE id=?";
			
			PreparedStatement pstmt = db.prepareStatement(sql);

			pstmt.executeQuery();
			
			ResultSet results = pstmt.getResultSet();
			
			results.next();
			
			StoreItem item = new StoreItem(
					results.getInt(1), //id
					results.getString(2), //name
					results.getDouble(3), //price
					results.getInt(4), //quantity
					results.getString(5) //image path
					);
			
			if(add) {
				/*String updateSql = "UPDATE items SET quantity=? WHERE id=?";
				
				PreparedStatement updateStatement = db.prepareStatement(sql);
				
				if(item.getQuantity() - quantity < 0) {
					doGet(request, response);
					return;
				}
				
				updateStatement.setInt(1, item.getQuantity() - quantity);
				updateStatement.setInt(2, id);

				updateStatement.executeQuery();*/
				//Put the above in checkout
				
				item.setQuantity(quantity);
				cart.addItem(item);
			} else {
				cart.removeItem(item.getId());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(db != null) db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("cart", cart);
		doGet(request, response);
	}

}
