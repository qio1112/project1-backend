package com.dao;

import com.entity.User;

/**
 * User DAO interface
 * @author yipeng
 */
public interface UserDao {
   public void setUserByUsername(String username, String password);
	User getUserByUsername(String username);
}