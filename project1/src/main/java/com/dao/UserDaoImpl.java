package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.UserEntity;

/**
 * User DAO implementation
 * @author yipeng
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public UserEntity getUserByUsername(String username) {
		
		List<UserEntity> users = new ArrayList<>();
		
		users = sessionFactory.getCurrentSession()
				.createQuery("from user where username=?")
				.setParameter(0, username)
				.getResultList();
		
		if(users.size() > 0) {
			return users.get(0);
		} 
		return null;
	}

}
