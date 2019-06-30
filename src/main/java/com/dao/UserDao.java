package com.dao;

import com.database.entity.User;

/**
 * User DAO interface
 * @author yipeng
 */
public interface UserDao {
   public void createUser(User newUser);
	User getUserByUsername(String username);
}