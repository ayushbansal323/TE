

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
 * Servlet implementation class updatefrm
 */
@WebServlet("/updatefrm")
public class updatefrm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatefrm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		   //finally block used to close resources

		
		
		
		
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
		String roll=request.getParameter("roll");
		String stuname="";
		 int sturoll = 0;
		 int stumark = 0;
		PrintWriter out=response.getWriter();
		  try {
	          // Register JDBC driver
	    	  Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	          Statement stmt = conn.createStatement();
	          String sql;
	          sql = "SELECT stuname,sturoll,stumark FROM student where sturoll="+roll;
	          ResultSet rs = stmt.executeQuery(sql);
	          out.println("<html><head><title></title></head><body>");
	       
	          
	          out.println("<body>");
	          while(rs.next()){
	        	  stuname = rs.getString("stuname");
	        	  sturoll=rs.getInt("sturoll");
	        	  stumark=rs.getInt("stumark");
	         }
	          out.println("<form action='update' method='post'>");
	  		out.println("Name <input type='text' name='sname' value="+ stuname+"></br>");
	  		out.println("Roll <input type='text' name='roll' value="+sturoll +"></br>");
	  		out.println("mark <input type='text' name='mark'value="+ stumark+" ></br>");
	  		out.println(" <input type='submit' value='Update'>");
	  		out.println("</form>");
	  		
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
