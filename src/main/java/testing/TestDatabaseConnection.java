package testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import  com.dao.UserDAO;


/**
 * testing the connection to database
 * change the property file "mysql.properties" 
 * use url "/testdb" to test connection
 * @author yipeng
 */

/**
 * 
 * @author Nicholas Marsden
 * After everyone was able to connect using jdbc connection.
 * Made the hibernate connection which is use to add the user 
 * to the database. In the users Table if it is there or not.
 *
 */
@WebServlet("/testdb")
public class TestDatabaseConnection extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TestDatabaseConnection.class.getName());
	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.addUser("nick", "pass");
            res.sendRedirect("Success");
        } catch (Exception e) {
 
            e.printStackTrace();
        }
	}
}
