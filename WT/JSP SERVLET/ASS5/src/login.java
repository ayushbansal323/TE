

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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL= "jdbc:mysql://localhost/test";
	  
	      final String USER = "root";
	      final String PASS = "";
	      
	      response.setContentType("text/html");
	      String uname=request.getParameter("username");
	      PrintWriter out = response.getWriter();
	      
	      out.println("<html>\n" +
	    	         "<head><title>Test Conneciton</title></head>\n" +
	    	         "<body bgcolor = \"#f0f0f0\">\n" +
	    	         "<h1 align = \"center\"></h1>\n");
	      out.println("<h1> welcome"+uname+"</h1>");
	      try {
	          // Register JDBC driver
	    	  Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	          Statement stmt = conn.createStatement();
	          String sql;
	          sql = "SELECT id, name, dept, salary FROM emp";
	          ResultSet rs = stmt.executeQuery(sql);
	          
	          while(rs.next()){
	              //Retrieve by column name
	              int id  = rs.getInt("id");
	              int sal = rs.getInt("salary");
	              String name = rs.getString("name");
	              String dept = rs.getString("dept");

	              //Display values
	              out.println("ID: " + id + "<br>");
	              out.println(", Age: " + sal + "<br>");
	              out.println(", First: " + name + "<br>");
	              out.println(", Last: " + dept + "<br>");
	           }
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
      
		doGet(request, response);
	}

}
