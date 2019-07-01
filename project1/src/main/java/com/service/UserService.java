package com.service;

import com.entity.UserEntity;

public interface UserService {
	
	void signup(UserEntity newUser);
	
	UserEntity getUserInfo(String username);
}
