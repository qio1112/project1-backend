package com.dao;

import com.entity.UserEntity;

/**
 * User DAO interface
 * @author yipeng
 */
public interface UserDao {
	
	UserEntity getUserByUsername(String username);
	
	void createUser(UserEntity newUser);
}
