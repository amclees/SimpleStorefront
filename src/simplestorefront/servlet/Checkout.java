package simplestorefront.servlet;

import java.io.IOException;
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
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StoreItem> items = DBUtil.getItems();
		if(request.getSession().getAttribute("cart") == null) {
			request.getSession().setAttribute("cart", new LinkedList<StoreItem>());
		}
		double totalPrice = 0;
		for(StoreItem item : (List<StoreItem>)request.getSession().getAttribute("cart")) {
			totalPrice += item.getQuantity() * item.getPrice();
		}
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("list", items);
		request.getRequestDispatcher("/WEB-INF/storefront/checkout.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Process Order
		
		response.sendRedirect("OrderComplete");
	}

}
