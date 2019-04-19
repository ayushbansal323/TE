

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class list
 */
@WebServlet("/list")
public class list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public list() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	    final String DB_URL= "jdbc:mysql://localhost/test";
	    final String USER = "root";
	    final String PASS = "";
		
		response.setContentType("text/html");
		String stuname="";
		 int sturoll;
		 int stumark;
		PrintWriter out=response.getWriter();
		  try {
	          // Register JDBC driver
	    	  Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	          Statement stmt = conn.createStatement();
	          String sql;
	          sql = "SELECT id, stuname,sturoll,stumark FROM student";
	          ResultSet rs = stmt.executeQuery(sql);
	          out.println("<html><head><title></title></head><body>");
	          out.println("<body><a href='/ASS5/dash.jsp'>Dashboard</a></br>");       
	          out.println("<table border=1><thead><tr><th>#</th><th>First Name</th><th>Mark</th></tr></thead><tbody>");
	          while(rs.next()){
	        	  stuname = rs.getString("stuname");
	        	  sturoll=rs.getInt("sturoll");
	        	  stumark=rs.getInt("stumark");
	        	  out.println("<tr><th scope='row'>"+sturoll+"</th><td>"+stuname+"</td><td>"+stumark+"</td></tr>");
	   	          }
	          out.println("</tbody></table>");
	          out.println("</body></html>");
	          
	           rs.close();
	           stmt.close();
	           conn.close();
	        } catch(SQLException se) {
	           //Handle errors for JDBC
	           se.printStackTrace();
	        } catch(Exception e) {
	           //Handle errors for Class.forName
	           e.printStackTrace();
	        } finally {
	            //finally block used to close resources
	        }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
