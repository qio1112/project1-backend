package com.routes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * testing the connection to database
 * change the property file "mysql.properties" 
 * use url "/testdb" to test connection
 * @author yipeng
 */


@RestController
public class InitialRoute {
	Logger logger = Logger.getLogger(InitialRoute.class.getName());
	
	@RequestMapping(value="/testdb", method= RequestMethod.GET)
	protected void initRoute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Resource resource = new ClassPathResource("/mysql.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");
		String url = props.getProperty("jdbc.url");
		String driverName = props.getProperty("jdbc.driver");
		
		logger.info("username: " + username + "\npassword: " + password + "\nurl: " + url + "\ndriver: " + driverName);
		
		Connection conn = null;

		try {
			PrintWriter out = res.getWriter();
			out.println("Connecting to mysql database.");
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			out.println("Success!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
