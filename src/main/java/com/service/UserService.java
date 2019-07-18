package com.service;

import com.entity.UserEntity;
import com.exception.DataAlreadyExistsException;
import com.exception.DataNotFoundException;

/**
 * service for user information
 * @author yipeng
 *
 */
public interface UserService {
	
	void signup(UserEntity newUser) throws DataAlreadyExistsException;
	
	UserEntity getUserInfo(String username) throws DataNotFoundException;
	
	String createUserToken(String username, String password);
}
