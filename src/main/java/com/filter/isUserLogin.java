package com.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.helper.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;

/**
 * @author Nicholaus
 * Servlet Filter implementation class isUserLogin
 */
@CrossOrigin(origins="*")
@WebFilter({"/project/*","/formula/*"})
@Component
public class isUserLogin implements Filter {
	
    /**
     * Default constructor. 
     */
    public isUserLogin() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		System.out.println("In filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
//		 String token = req.getHeader("token");
//		 
		String token = req.getHeader("Authorization");
		System.out.println("Get token ");
		System.out.println(token);
		System.out.println("res :");
		System.out.println(res);
//		System.out.println(token);
//////		 // if token doesnt exist and if its not valid 
//		 if(token == null || !getToken(token)) {			
////			 res.sendRedirect("http://localhost:8080/project1");
//			 res.sendError(403);
//			 return;
//		 }
		 System.out.println("you are in your application good job");
		 // pass the request along the filter chain
		 chain.doFilter(request, response);
	}
	
	public boolean getToken(String token) {
		// TODO Auto-generated method stub
		try {
			// if claim doesnt throw an error
			Claims claims = JWT.decodeJWT(token);
			// then we get the mapping of it take the user naem from claim
			Map<String, Object> expectedMap = new HashMap<>(claims);
			// then check the user name of the token from the claim
			return true;	
		} catch(Exception e) {
			return false;
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}



//System.out.println( "iss " + expectedMap.get("iss"));
//System.out.println( "id " + expectedMap.get("id"));
//   for(Entry<String, Object> entry : claims.entrySet()) {
//	   System.out.println(entry.getKey() + " : " + entry.getValue() );
////        expectedMap.put(entry.getKey() , entry.getValue());
//    }
