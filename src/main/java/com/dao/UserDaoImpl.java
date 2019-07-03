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
	
		if(users.size() > 0) {
			
			return jwt.createJWT("Looking up at the clouds", users.get(0).getUsername(), users.get(0).getMemberDate().toString(), -1);
		} 
		return null;
	}

	@Override
	public void createUser(UserEntity newUser) {
		newUser.setPassword(decoder.encrypt(newUser.getPassword()));
		System.out.println(newUser.toString());
		sessionFactory.getCurrentSession()
			.saveOrUpdate(newUser);
	}

//	@Override
//	public void createUser(UserEntity newUser) {
//		
//		sessionFactory.getCurrentSession()
//			.saveOrUpdate(newUser);
//	}
	
	

}
