package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.UserEntity;
import com.exception.DataAlreadyExistsException;
import com.exception.DataNotFoundException;

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
	public void signup(UserEntity newUser) throws DataAlreadyExistsException {

		userDao.createUser(newUser);
	}

	@Override
	public UserEntity getUserInfo(String username) throws DataNotFoundException {
		
		return userDao.getUserByUsername(username);
	}

	@Override
	public String createUserToken(String username, String password) {
		
		return userDao.createUserToken(username, password);
	}

}
