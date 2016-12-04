package simplestorefront.servlet;

import simplestorefront.models.DBUtil;
import simplestorefront.models.StoreItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/Store")
public class Storefront extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<StoreItem> items = DBUtil.getItems();
    if (request.getSession().getAttribute("cart") == null) {
      request.getSession().setAttribute("cart", new LinkedList<StoreItem>());
    }
    request.setAttribute("list", items);
    request.getRequestDispatcher("/WEB-INF/storefront/storefront.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  }
}
