package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.UserEntity;
import com.exception.DataAlreadyExistsException;
import com.exception.DataNotFoundException;
import com.helper.JWT;
import com.helper.TrippleDes;

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
	public UserEntity getUserByUsername(String username) throws DataNotFoundException {
		
		List<UserEntity> users = sessionFactory.getCurrentSession()
				.createQuery("from UserEntity where username=:username", UserEntity.class)
				.setParameter("username", username)
				.getResultList();
		if(users.size() > 0)
			return users.get(0);
		else 
			throw new DataNotFoundException("user not found");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String createUserToken(String username, String password) {
		
		System.out.println(username + " " + password);
		List<UserEntity> users = new ArrayList<>();
		users = sessionFactory.getCurrentSession()
				.createQuery("FROM UserEntity where username=:username and password=:password")
				.setParameter("username", username)
				.setParameter("password", decoder.encrypt(password).toString())
				.getResultList();
	
		if(users.size() > 0) {
			return jwt.createJWT("Looking up at the clouds", users.get(0).getUsername(), users.get(0).getMemberDate().toString(), UserDao.TOKEN_EXPIRES);
		} 
		return null;
	}

	@Override
	public void createUser(UserEntity newUser) throws DataAlreadyExistsException {
		
		try {
			// check if username already exists
			UserEntity user = this.getUserByUsername(newUser.getUsername());
			throw new DataAlreadyExistsException("Username already exists");
		} catch (DataNotFoundException e) {
			newUser.setPassword(decoder.encrypt(newUser.getPassword()));
			System.out.println(newUser.toString());
			sessionFactory.getCurrentSession()
				.saveOrUpdate(newUser);
		}
	}

//	@Override
//	public void createUser(UserEntity newUser) {
//		
//		sessionFactory.getCurrentSession()
//			.saveOrUpdate(newUser);
//	}
	
	

}
