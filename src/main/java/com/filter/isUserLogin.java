package com.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

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

import com.helper.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;

/**
 * @author Nicholaus
 * Servlet Filter implementation class isUserLogin
 */
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
		
		System.out.println("HEADER:" + req.getHeader("authorization"));
		// do filter
//		res.sendError(403, "Failed to auth");
		
		chain.doFilter(request, response);
	}
	
	public boolean getToken(String token) {
		// TODO Auto-generated method stub
		try {
			// if claim doesn't throw an error
			Claims claims = JWT.decodeJWT(token);
			// then we get the mapping of it take the user name from claim
			Map<String, Object> expectedMap = new HashMap<>(claims);
			// then check the user name of the token from the claim
			System.out.println(expectedMap.keySet());
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
