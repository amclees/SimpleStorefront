package simplestorefront.servlet;

import simplestorefront.models.DBUtil;
import simplestorefront.models.StoreItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect("Store");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int quantity = 0;
    int id = -1;
    boolean remove = false;
    try {
      quantity = Integer.parseInt(request.getParameter("quantity"));
      if (quantity <= 0) throw new Exception();
      id = Integer.parseInt(request.getParameter("id"));
    } catch (Exception e) {
      response.sendRedirect("Store");
      return;
    }
    try {
      remove = request.getParameter("cart").equals("true") ? true : false;
    } catch (Exception e) {
    }

    List<StoreItem> items;
    List<StoreItem> db = DBUtil.getItems();

    try {
      items = (List<StoreItem>) request.getSession().getAttribute("cart");
      items.size();
    } catch (Exception e) {
      e.printStackTrace();
      response.sendRedirect("Store");
      return;
    }

    int toChange = -1;
    for (StoreItem item : items) {
      if (item.getId() == id) toChange = items.indexOf(item);
    }

    int dbIndex = -1;
    for (StoreItem item : db) {
      if (item.getId() == id) dbIndex = db.indexOf(item);
    }
    if (dbIndex == -1) {

      response.sendRedirect("Store");
      return;
    }


    StoreItem item;
    try {
      item = items.get(toChange);
    } catch (Exception e) {
      StoreItem dbItem = db.get(dbIndex);
      item = new StoreItem(dbItem.getId(), dbItem.getName(), dbItem.getPrice(), 0, dbItem.getImagePath(), dbItem.getDetails());
      item.setQuantity(0);
    }

    if (!remove) {
      int potentialSum = (-1 * (quantity + item.getQuantity())) + db.get(dbIndex).getQuantity();

      if (potentialSum < 0) {
        response.sendRedirect("Store");
        return;
      }


    } else {
      quantity *= -1;
      int potentialSum = quantity + item.getQuantity();

      if (potentialSum < 0) {
        response.sendRedirect("Store");
        return;
      }
    }

    item.setQuantity(quantity + item.getQuantity());

    if (!items.contains(item)) {
      items.add(item);
    } else if (item.getQuantity() == 0) {
      items.remove(item);
    }


    request.getSession().setAttribute("cart", items);

    response.sendRedirect("Store");
  }
}
