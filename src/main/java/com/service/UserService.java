package com.service;

import com.entity.UserEntity;

/**
 * service for user information
 * @author yipeng
 *
 */
public interface UserService {
	
	String signup(UserEntity newUser);
	
	UserEntity getUserInfo(String username);
	
	String createUserToken(String username, String password);
}
