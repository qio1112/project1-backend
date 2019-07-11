package com.dao;

import com.entity.UserEntity;

/**
 * User DAO interface
 * @author yipeng
 */
public interface UserDao {
	
	UserEntity getUserByUsername(String username);
	
	String createUser(UserEntity newUser);
	
	public String createUserToken(String username, String password);
	
}
