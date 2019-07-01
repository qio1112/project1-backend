package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.UserEntity;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void signup(UserEntity newUser) {

		userDao.createUser(newUser);
	}

	@Override
	public UserEntity getUserInfo(String username) {
		
		return userDao.getUserByUsername(username);
	}

}
