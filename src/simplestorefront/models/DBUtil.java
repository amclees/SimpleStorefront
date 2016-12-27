package simplestorefront.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import simplestorefront.models.StoreItem;

public class DBUtil {

  //Has potential to delete entire database
	/*
	public synchronized static void pushItems(List<StoreItem> items) {
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

      String sql = "DELETE FROM items";
      

      PreparedStatement pstmt = db.prepareStatement(sql);

      pstmt.executeUpdate();

      sql = "INSERT INTO items (id,name,price,quantity,imagepath,details) VALUES (?,?,?,?,?,?)";
      PreparedStatement addStatement = db.prepareStatement(sql);
      for (StoreItem item : items) {
        addStatement.setInt(1, item.getId());
        addStatement.setString(2, item.getName());
        addStatement.setDouble(3, item.getPrice());
        addStatement.setInt(4, item.getQuantity());
        addStatement.setString(5, item.getImagePath());
        addStatement.setString(6, item.getDetails());
        addStatement.executeUpdate();
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (db != null) db.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }*/

  public static List<StoreItem> getItems() {
    List<StoreItem> items = new LinkedList<StoreItem>();

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

      String sql = "SELECT * FROM items";

      PreparedStatement pstmt = db.prepareStatement(sql);

      pstmt.executeQuery();

      ResultSet results = pstmt.getResultSet();

      while (results.next()) {
        items.add(new StoreItem(
            results.getInt(1), //id
            results.getString(2), //name
            results.getDouble(3), //price
            results.getInt(4), //quantity
            results.getString(5), //image path
            results.getString(6)
        ));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (db != null) db.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return items;
  }
}
