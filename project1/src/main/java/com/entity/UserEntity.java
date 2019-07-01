package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserEntity {
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="member_date")
	private Date memberDate;
	
	public UserEntity() { }

	public UserEntity(String username, String password, String name, Date memberDate) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.memberDate = memberDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}

	@Override
	public String toString() {
		return "UserEntity [username=" + username + ", password=" + password + ", name=" + name + ", memberDate="
				+ memberDate + "]";
	}
	
}
