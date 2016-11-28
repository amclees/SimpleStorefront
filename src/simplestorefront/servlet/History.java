package simplestorefront.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simplestorefront.models.LogItem;
import simplestorefront.models.StoreItem;

/**
 * Servlet implementation class History
 */
@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<LogItem> items = new LinkedList<LogItem>();
		
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
			
			String sql = "SELECT * FROM logs";
			
			PreparedStatement pstmt = db.prepareStatement(sql);

			pstmt.executeQuery();
			
			ResultSet results = pstmt.getResultSet();
			
			while(results.next()) {
				items.add(new LogItem(
						results.getString(2), //name
						results.getString(3), //description
						new Date(results.getTimestamp(1).getTime())//timestamp
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
		request.getRequestDispatcher("/WEB-INF/dummyhistory.jsp").forward(request, response);;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
