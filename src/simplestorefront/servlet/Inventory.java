package simplestorefront.servlet;

import simplestorefront.models.DBUtil;
import simplestorefront.models.SQLAuth;
import simplestorefront.models.StoreItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@WebServlet("/Inventory")
public class Inventory extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<StoreItem> items = DBUtil.getItems();
    request.setAttribute("list", items);
    request.getRequestDispatcher("/WEB-INF/storefront/inventory.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String ids = request.getParameter("id");
    String delete = request.getParameter("delete");
    if (delete != null) {
      int id;
      try {
        id = Integer.parseInt(ids);
      } catch (Exception e) {
        response.sendRedirect("Inventory");
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

        String sql = "DELETE FROM items WHERE id=?";
        PreparedStatement pstmt = db.prepareStatement(sql);

        pstmt.setInt(1, id);

        pstmt.executeUpdate();

      } catch (Exception e) {
        e.printStackTrace();
        doGet(request, response);
        return;
      } finally {
        try {
          if (db != null) {
            db.close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } else if (name != null) {
      int quantity = -1;
      double price = -1.0d;
      String imagePath = "";
      String details = "";
      try {
        quantity = Integer.parseInt(request.getParameter("quantity"));
        price = Double.parseDouble(request.getParameter("price"));
        imagePath = "";
        details = request.getParameter("details") + "";
      } catch (Exception e) {
        response.sendRedirect("Inventory");
        return;
      }

      StoreItem item = new StoreItem(-1, name, price, quantity, imagePath, details);

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

        String sql = "INSERT INTO items (name,price,quantity,imagepath,details) VALUES (?,?,?,?,?)";
        PreparedStatement addStatement = db.prepareStatement(sql);

        addStatement.setString(1, item.getName());
        addStatement.setDouble(2, item.getPrice());
        addStatement.setInt(3, item.getQuantity());
        addStatement.setString(4, item.getImagePath());
        addStatement.setString(5, item.getDetails());
        addStatement.executeUpdate();


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
    } else if (ids != null) {
      int id, quantity;
      try {
        id = Integer.parseInt(ids);
        quantity = Integer.parseInt(request.getParameter("quantity"));
      } catch (Exception e) {
        doGet(request, response);
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

        String sql = "SELECT * FROM items WHERE id=?";

        PreparedStatement pstmt = db.prepareStatement(sql);

        pstmt.setInt(1, id);

        pstmt.executeQuery();

        ResultSet results = pstmt.getResultSet();

        results.next();
        StoreItem item = new StoreItem(
            results.getInt(1), //id
            results.getString(2), //name
            results.getDouble(3), //price
            results.getInt(4), //quantity
            results.getString(5), //image path
            results.getString(6)
        );

        if (item.getQuantity() + quantity < 0) {
          doGet(request, response);
          return;
        }


        sql = "UPDATE items SET quantity=? WHERE id=?";
        pstmt = db.prepareStatement(sql);

        pstmt.setInt(1, quantity + item.getQuantity());
        pstmt.setInt(2, id);


        pstmt.executeUpdate();

      } catch (Exception e) {
        e.printStackTrace();
        doGet(request, response);
        return;
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

    response.sendRedirect("Inventory");
  }
}
