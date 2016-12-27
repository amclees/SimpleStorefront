package simplestorefront.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simplestorefront.models.DBUtil;
import simplestorefront.models.Order;
import simplestorefront.models.SQLAuth;
import simplestorefront.models.StoreItem;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid = request.getParameter("oid");
		if (oid != null) {
			int id = -1;
			try {
				id = Integer.parseInt(oid);
			} catch (Exception e) {
			}
			if (id != -1) {
				request.setAttribute("id", id);
				request.getRequestDispatcher("/WEB-INF/storefront/orderconfirmation.jsp").forward(request, response);
				return;
			}
		}

		List<StoreItem> items = DBUtil.getItems();
		if (request.getSession().getAttribute("cart") == null) {
			request.getSession().setAttribute("cart", new LinkedList<StoreItem>());
		}
		double totalPrice = 0;
		for (StoreItem item : (List<StoreItem>) request.getSession().getAttribute("cart")) {
			totalPrice += item.getQuantity() * item.getPrice();
		}
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("list", items);
		request.getRequestDispatcher("/WEB-INF/storefront/checkout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName, email;
		try {
			String firstName = request.getParameter("firstname").trim();
			String lastName = request.getParameter("lastname").trim();
			if (firstName.equals("") || lastName.equals("")) {
				throw new Exception();
			}
			fullName = firstName + " " + lastName;

			email = request.getParameter("email");
			int atCount = email.length() - email.replace("@", "").length();
			if (atCount != 1) {
				throw new Exception();
			}
			String domain = email.split("@")[1];
			int dotCount = domain.length() - domain.replace(".", "").length();
			if (dotCount != 1) {
				throw new Exception();
			}

			// There is not way to make sure email works for sure, so this should do
			// -- amclees
		} catch (Exception e) {
			// e.printStackTrace();
			response.sendRedirect("Checkout");
			return;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = SQLAuth.getUrl();
		String user = SQLAuth.getUser();
		String password = SQLAuth.getPassword();

		Connection db = null;

		try {
			db = DriverManager.getConnection(url, user, password);

			// Process Order
			List<StoreItem> store = new LinkedList<StoreItem>();

			String sql = "SELECT * FROM items";

			PreparedStatement pstmt = db.prepareStatement(sql);

			pstmt.executeQuery();

			ResultSet results = pstmt.getResultSet();

			while (results.next()) {
				store.add(new StoreItem(results.getInt(1), // id
						results.getString(2), // name
						results.getDouble(3), // price
						results.getInt(4), // quantity
						results.getString(5), // image path
						results.getString(6)));
			}

			List<StoreItem> cart = (List<StoreItem>) request.getSession().getAttribute("cart");
			Map<StoreItem, Integer> changes = new HashMap<StoreItem, Integer>();
			for (StoreItem item : cart) {
				int currentId = item.getId();
				StoreItem storeItem = null;
				for (StoreItem sitem : store) {
					if (sitem.getId() == currentId) {
						storeItem = sitem;
						break;
					}
				}
				int newQuantity = storeItem.getQuantity() - item.getQuantity();
				if (newQuantity < 0) {
					response.sendRedirect("Checkout");
					return;
				}
				changes.put(item, newQuantity);
			}

			sql = "UPDATE items SET quantity=? WHERE id=?";
			PreparedStatement updateQuantity = db.prepareStatement(sql);
			for (StoreItem key : changes.keySet()) {
				int newQuantity = changes.get(key);
				updateQuantity.setInt(1, newQuantity);
				updateQuantity.setInt(2, key.getId());
				updateQuantity.executeUpdate();
			}

			Order order = new Order(-1, cart, fullName + " with email " + email);

			int oid = -1;

			sql = "INSERT INTO logs (text) VALUES (?)";
			PreparedStatement addStatement = db.prepareStatement(sql);

			addStatement.setString(1, order.getText());

			addStatement.executeUpdate();

			sql = "SELECT id FROM logs WHERE text=?";
			PreparedStatement grab = db.prepareStatement(sql);
			grab.setString(1, order.getText());
			grab.executeQuery();
			ResultSet orderResults = grab.getResultSet();
			orderResults.next();
			oid = orderResults.getInt(1);
			request.getSession().setAttribute("cart", new LinkedList<StoreItem>());

			response.sendRedirect("Checkout?oid=" + oid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (db != null) {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
