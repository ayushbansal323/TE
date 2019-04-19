

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class check
 */
@WebServlet("/check")
public class check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public check() {
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
		String name=request.getParameter("username");
		String pass=request.getParameter("password");
		String dname="";
		 String dpass="";
		PrintWriter out=response.getWriter();
		  try {
	          // Register JDBC driver
	    	  Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	          Statement stmt = conn.createStatement();
	          String sql;
	          sql = "SELECT username,password FROM users";
	          ResultSet rs = stmt.executeQuery(sql);
	          while(rs.next()){
	           dname = rs.getString("username");
	           dpass = rs.getString("password");
	          }
	          out.println("<html><body>");
	          if(name.equalsIgnoreCase(dname) && pass.equalsIgnoreCase(dpass)){
	        	  out.println("<h1>Login Successfully</h1>");
	        	  Cookie uname=new Cookie("uname",dname);
	        	  uname.setMaxAge(60*60*5);
	        	  response.addCookie(uname);
	        	  response.sendRedirect("dash.jsp");
	          }else{
	        	  out.println("<h1>wrong username or password</h1>");
	        	  out.println("<a href='/ASS5/'>login</a>");
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
