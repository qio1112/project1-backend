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
		Query<ProjectEntity> query = session.createQuery("from ProjectEntity where id=:id", ProjectEntity.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public ProjectEntity getProjectByName(String projectName) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<ProjectEntity> query = session.createQuery("from ProjectEntity where projectName=:projectName order by id", ProjectEntity.class);
		query.setParameter("projectName", projectName);
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
		session.save(newProject);
	}

}
