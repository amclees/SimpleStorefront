package simplestorefront.servlet;

import simplestorefront.models.Order;

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
import java.util.LinkedList;
import java.util.List;

@WebServlet("/History")
public class History extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Order> items = new LinkedList<>();

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

      String sql = "SELECT * FROM logs ORDER BY id DESC";

      PreparedStatement pstmt = db.prepareStatement(sql);

      pstmt.executeQuery();

      ResultSet results = pstmt.getResultSet();

      while (results.next()) {
        items.add(new Order(
            results.getInt(1),
            results.getString(2)
        ));
      }

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

    request.setAttribute("items", items);
    request.getRequestDispatcher("/WEB-INF/storefront/history.jsp").forward(request, response);

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
