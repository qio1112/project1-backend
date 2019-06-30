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
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.database.entity.User;




@Repository
public class UserDaoImpl  implements UserDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional
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
