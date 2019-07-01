package com.service;

import com.entity.UserEntity;

/**
 * service for user information
 * @author yipeng
 *
 */
public interface UserService {
	
	void signup(UserEntity newUser);
	
	UserEntity getUserInfo(String username);
}
