package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.UserEntity;
import com.helper.JWT;
import com.helper.TrippleDes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;

/**
 * User DAO implementation
 * @author yipeng
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private TrippleDes decoder;
	
	@Autowired
	private JWT jwt;
	
	@Override
	@SuppressWarnings("unchecked")
	public UserEntity getUserByUsername(String username) {
		List<UserEntity> users = new ArrayList<>();	
		users = sessionFactory.getCurrentSession()
				.createQuery("from UserEntity where username=:username")
				.setParameter("username", username)
				.getResultList();
		System.out.println(users);
		
		return users.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String createUserToken(String username, String password) {

		List<UserEntity> users = new ArrayList<>();
		users = sessionFactory.getCurrentSession()
				.createQuery("FROM UserEntity where username=:username and password=:password")
				.setParameter("username", username)
				.setParameter("password", decoder.encrypt(password).toString())
				.getResultList();
		System.out.println(users);
		if(users.size() > 0) {
			//UserNames the id
			//issue is the name
			//subject is the memberdate in string format
			
			// token expirse in 1 hour
			System.out.println(users);
			return jwt.createJWT( users.get(0).getName() ,users.get(0).getUsername() , users.get(0).getMemberDate().toString(), 1000*60*60*24);
		} 
		return null;
	}

	@Override
	public String createUser(UserEntity newUser) {
		String error="";
		try {
			newUser.setPassword(decoder.encrypt(newUser.getPassword()));
//			System.out.println(newUser.toString());
			sessionFactory.getCurrentSession()
				.saveOrUpdate(newUser);
			return "successfully register user";
		}catch(Exception ex) {
			error = ex.getMessage();
		}
		return error;
		
	}

//	@Override
//	public void createUser(UserEntity newUser) {
//		
//		sessionFactory.getCurrentSession()
//			.saveOrUpdate(newUser);
//	}
	
	

}
