package com.service;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.UserEntity;

@Service("userDetailsService")
public class TheUserDetailsService implements UserDetailsService{
	
	@Autowired 
	private UserDao userDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userDao.getUserByUsername(username);
		return buildUserForAuthentication(userEntity);
	}
	
	private User buildUserForAuthentication(UserEntity userEntity) {
		return new User(userEntity.getUsername(), userEntity.getPassword(), new HashSet<GrantedAuthority>());
	}
	
//	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

//		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//		// Build user's authorities
//		for (UserRole userRole : userRoles) {
//			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//		}
//
//		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//		return null;
//	}

}
