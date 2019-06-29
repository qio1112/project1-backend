package com.entity;

//import javax.persistence.Basic;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 
 * @author Nicholas Marsden
 * This POJO is connected with 
 * User.hbm.xml in testing package for now.
 * hibernate.cfg.xml in src/main/java 
 * User.hbm.xml was use to configure the id auto generator and type
 *
 *hibernate.cfg.cml was use to connect to the database and automatically
 *create if doesn't exist and add a record(update)
 *or 
 *add a record(update)
 */
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@Basic(optional = false)
//    @Column(name = "id") 
	long id;
//	@Column(name = "username") 
	String userName;
//	@Column(name = "password") 
	String password;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
