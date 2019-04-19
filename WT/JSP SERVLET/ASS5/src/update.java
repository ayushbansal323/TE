

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
 * Servlet implementation class update
 */
@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
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
		String stuname=request.getParameter("sname");
		int sturoll=Integer.parseInt(request.getParameter("roll"));
		int stumark=Integer.parseInt(request.getParameter("mark"));
		PrintWriter out=response.getWriter();
		  try {
	          // Register JDBC driver
	    	  Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	          Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	         PreparedStatement pst=(PreparedStatement) conn.prepareStatement("update student set stuname=?, sturoll=?, stumark=? where sturoll=?");
	         pst.setString(1,stuname);
	         pst.setInt(2, sturoll);
	         pst.setInt(3, stumark);
	         pst.setInt(4, sturoll);
	         int i= pst.executeUpdate();
	         
	         if(i!=0){
	        	 msg="Record has been Updated";
	             out.println("<font size='6' color=blue>" + msg + "</font>"); 
	             out.println("<a href='/ASS5/dash.jsp'>DashBoard<a>");
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
