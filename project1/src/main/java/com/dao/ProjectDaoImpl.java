package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.ProjectEntity;

/**
 * DAO of project information
 * @author yipeng
 *
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<ProjectEntity> getProjects() {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ProjectEntity> query = session.createQuery("from ProjectEntity order by id", ProjectEntity.class);
		return query.getResultList();
	}

	@Override
	public ProjectEntity getProjectById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ProjectEntity> query = session.createQuery("from ProjectEntity where id=? order by id", ProjectEntity.class);
		query.setParameter(0, id);
		return query.getSingleResult();
	}

	@Override
	public ProjectEntity getProjectByName(String name) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ProjectEntity> query = session.createQuery("from ProjectEntity where name=? order by id", ProjectEntity.class);
		query.setParameter(0, name);
		return query.getSingleResult();
	}
	
	//insert
	@Override
	public void insertProject(String name) {
		
		Session session = sessionFactory.getCurrentSession();
		ProjectEntity newProject = new ProjectEntity(name);
		session.saveOrUpdate(newProject);
	}

	@Override
	public void insertProject(ProjectEntity newProject) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(newProject);
	}

}
