package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.UserEntity;

/**
 * service for user information
 * @author yipeng
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public String signup(UserEntity newUser) {

		return userDao.createUser(newUser);
	}

	@Override
	public UserEntity getUserInfo(String username) {
		
		return userDao.getUserByUsername(username);
	}

	@Override
	public String createUserToken(String username, String password) {
		
		return userDao.createUserToken(username, password);
	}

}
