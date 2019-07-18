package com.dao;

import com.entity.UserEntity;
import com.exception.DataAlreadyExistsException;
import com.exception.DataNotFoundException;

/**
 * User DAO interface
 * @author yipeng
 */
public interface UserDao {
	
	public static final int TOKEN_EXPIRES = 3600000;
	
	UserEntity getUserByUsername(String username) throws DataNotFoundException;
	
	void createUser(UserEntity newUser) throws DataAlreadyExistsException;
	
	public String createUserToken(String username, String password);
	
}
