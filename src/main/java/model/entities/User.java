package model.entities;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.Basic;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;



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
@Repository
public class User  implements UserDao{
	@Autowired
	SessionFactory sessionFactory;
    DataSource dataSource;
	long id;

	String userName;
	String password;
	String name;
	String member_date;
	//GETTERS
	public long getId() {return id;}
	public String getName() {return name;}
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	public String getMember_date() {return member_date;}
	// SETTERS
	public User setId(long id) {
		this.id = id;
		return this;
	}
	public User setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}
	public User setMember_date(String member_date) {
		this.member_date = member_date;
		return this;
	}


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
	@Override
	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
		
		List<User> users = new ArrayList<>();
		
		users = sessionFactory.getCurrentSession()
				.createQuery("from user where username=?")
				.setParameter(0, username)
				.getResultList();
		
		if(users.size() > 0) {
			return users.get(0);
		} 
		return null;
	}

	@Override
	public void createUser(User newUser) {
		
		sessionFactory.getCurrentSession()
			.saveOrUpdate(newUser);
	}
	
	
}
