		

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class insertuser
 */
@WebServlet("/insertuser")
public class insertuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertuser() {
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
		String msg="";
		response.setContentType("text/html");
		String uname=request.getParameter("name");
		String pass=request.getParameter("pass");
		PrintWriter out=response.getWriter();
		  try {
	          // Register JDBC driver
	    	  Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	         PreparedStatement pst=(PreparedStatement) conn.prepareStatement("insert into users(username,password,block)values(?,?,0)");
	         pst.setString(1,uname);
	         pst.setString(2, pass);
	         int i= pst.executeUpdate();
	         
	         if(i!=0){
	        	 msg="Record has been inserted";
	             out.println("<font size='6' color=blue>" + msg + "</font>"); 
	             out.println("<a href='/ASS5/'>Add More<a>");
	         }
	         else{  
	             msg="failed to insert the data";
	             out.println("<font size='6' color=blue>" + msg + "</font>");
	            }  
	  	          out.println("</body></html>");
	                   
	           pst.close();
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
