package com.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.User;

/**
 * User DAO implementation
 * @author yipeng
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
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
	public void setUserByUsername(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

//        try {
//            // 1. configuring hibernate
//            Configuration configuration = new Configuration().configure();
// 
//            // 2. create sessionfactory
//            SessionFactory sessionFactory = configuration.buildSessionFactory();
// 
//            // 3. Get Session object
//            Session session = sessionFactory.openSession();
// 
//            // 4. Starting Transaction
//            Transaction transaction = session.beginTransaction();
//            session.save(user);
//            transaction.commit();
//            System.out.println("\n\n User Added \n");
// 
//        } catch (HibernateException e) {
//            System.out.println(e.getMessage());
//            System.out.println("error");
//        }
		
		try {
            // 3. Get Session object
            Session session = sessionFactory.getCurrentSession();
//            sessionFactory.openSession();
            // 4. Starting Transaction
//            Transaction transaction = 
//            session.beginTransaction();
            session.save(user);
//            transaction.commit();
//            session.getTransaction().commit();
            System.out.println("\n\n User Added \n");
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
	}

}
