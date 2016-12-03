package simplestorefront.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simplestorefront.models.StoreItem;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/ShoppingCart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("cart") == null) {
			request.getSession().setAttribute("cart", new LinkedList<StoreItem>());
		}
		double totalPrice = 0;
		for(StoreItem item : (List<StoreItem>)request.getSession().getAttribute("cart")) {
			totalPrice += item.getQuantity() * item.getPrice();
		}
		request.setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("/WEB-INF/storefront/cart.jsp").forward(request, response);
	}
	
}
